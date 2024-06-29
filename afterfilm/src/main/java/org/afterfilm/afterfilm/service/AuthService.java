package org.afterfilm.afterfilm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.afterfilm.afterfilm.domain.User.User;
import org.afterfilm.afterfilm.dto.user.*;
import org.afterfilm.afterfilm.exception.CustomException;
import org.afterfilm.afterfilm.exception.error.ErrorCode;
import org.afterfilm.afterfilm.jwt.TokenProvider;
import org.afterfilm.afterfilm.repository.UserMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public UserResponseDto signup(SignUpRequestDto signUpRequestDto) {
        if (userMapper.existsByEmail(signUpRequestDto.getEmail())) {
            throw new CustomException(ErrorCode.USER_DUPLICATION);
        }

        User user = signUpRequestDto.toUser(passwordEncoder);

        userMapper.insertUser(user);

        return UserResponseDto.of(user);
    }

    @Transactional
    public TokenDto login(LoginDto loginDto) {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = loginDto.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        // authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.createTokenDto(authentication);

        // 4. RefreshToken 저장
        User user = userMapper.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.NO_LOGIN));

        user.updateToken(tokenDto.getRefreshToken());
        userMapper.updateUser(user);

        return tokenDto;
    }

    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        TokenDto tokenDto;
        User user;

        // 1. access Token이 유효한 경우
        if (tokenProvider.validateToken(tokenRequestDto.getAccessToken())) {
            // 1-1. access Token에서 User ID 가져오기
            Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

            // 1-2. 저장소에서 User ID 를 기반으로 Refresh Token 값 가져옴
            user = userMapper.findById(Long.parseLong(authentication.getName()))
                    .orElseThrow(() -> new CustomException(ErrorCode.NO_LOGIN));

            // 1-3. Refresh Token 일치하는지 검사
            if (!user.getRefreshToken().equals(tokenRequestDto.getRefreshToken())) {
                throw new CustomException(ErrorCode.NO_USER);
            }

            // 1-4. 새로운 access Token 생성
            tokenDto = tokenProvider.createTokenDto(authentication);
        }
        // 2. access Token이 만료된 경우
        else {
            // 2-1. Refresh Token 검증
            if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
                throw new CustomException(ErrorCode.NO_TOKEN);
            }

            // 2-2. 저장소에서 Refresh Token 값 가져옴
            user = userMapper.findByRefreshToken(tokenRequestDto.getRefreshToken())
                    .orElseThrow(() -> new CustomException(ErrorCode.NO_USER));

            // 2-3. 새로운 access Token 생성
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUserId(), null, new ArrayList<>());
            tokenDto = tokenProvider.createTokenDto(authentication);
        }

        // 3. 저장소 정보 업데이트
        User newRefreshToken = user.updateToken(tokenDto.getRefreshToken());
        userMapper.updateUser(newRefreshToken);

        return tokenDto;
    }

    public boolean checkEmail(String email) {
        return userMapper.existsByEmail(email);
    }

}
