package org.afterfilm.afterfilm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.afterfilm.afterfilm.domain.User.User;
import org.afterfilm.afterfilm.dto.user.UserRequestDto;
import org.afterfilm.afterfilm.dto.user.UserResponseDto;
import org.afterfilm.afterfilm.exception.CustomException;
import org.afterfilm.afterfilm.exception.error.ErrorCode;
import org.afterfilm.afterfilm.repository.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Transactional(readOnly = true)
    public UserResponseDto findMemberInfoByUserId(Long userId) {
        return userMapper.findById(userId)
                .map(UserResponseDto::of)
                .orElseThrow(() -> new CustomException(ErrorCode.NO_USER));
    }

    @Transactional(readOnly = true)
    public UserResponseDto findUserInfoByEmail(String email) {
        return userMapper.findByEmail(email)
                .map(UserResponseDto::of)
                .orElseThrow(() -> new CustomException(ErrorCode.NO_USER));
    }

    @Transactional
    public void logout(Long userId) {
        User user = userMapper.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NO_USER));
        user.updateToken(null);
        userMapper.updateUser(user);
    }

    @Transactional
    public UserResponseDto updateUser(UserRequestDto userRequestDto) throws IOException {
        User user = userMapper.findByEmail(userRequestDto.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.NO_USER));

        user.updateImg(userRequestDto.getUserImage());

        user.updateUser(userRequestDto.getNickname());
        userMapper.updateUser(user);
        return UserResponseDto.of(user);
    }

    // To do
    // 1. delete 구현
    // 2. 비밀번호 변경
}
