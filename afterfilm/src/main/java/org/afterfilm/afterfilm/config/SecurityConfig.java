package org.afterfilm.afterfilm.config;

import lombok.RequiredArgsConstructor;
import org.afterfilm.afterfilm.exception.JwtAccessDeniedHandler;
import org.afterfilm.afterfilm.exception.JwtAuthenticationEntryPoint;
import org.afterfilm.afterfilm.jwt.JwtFilter;
import org.afterfilm.afterfilm.jwt.JwtSecurityConfig;
import org.afterfilm.afterfilm.jwt.TokenProvider;
import org.afterfilm.afterfilm.repository.UserMapper;
import org.afterfilm.afterfilm.service.CustomOauth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint; // 유효한 자격 증명을 제공하지 않고 접근하려고 할때 401
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler; // 필요한 권한이 존재하지 않은 경우 403 에러

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    private final UserMapper userMapper;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        CustomOauth2UserService customOauth2UserService = new CustomOauth2UserService(userMapper, passwordEncoder());

        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF 설정 Disable (토큰을 사용하기 때문)

                // exception handling
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler))

                .headers((headers) -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))

                // Security는 기본적으로 세션을 사용하지만 JWT 처리는 세션 설정을 Stateless로 설정 (사용 X)
                .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 로그인, 회원가입 API는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정
                .authorizeRequests((authorizeRequest) -> authorizeRequest
                        .requestMatchers("/api/user", "/api/user/login", "/api/user/access", "/api/user/check-email/{email}").permitAll()
                        .anyRequest().authenticated()) // 나머지 API는 전부 인증 필요
                // / JwtFilter를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
                .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)

                .cors((cors) -> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);       // 서버의 json 응답을 JS로 처리가능하게 함
        config.addAllowedOriginPattern("*");    // springboot cors 설정 시, allowCredentials(true)와 allowedOrigin("*") 같이 사용 불가하게 업뎃
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
