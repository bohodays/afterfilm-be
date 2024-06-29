package org.afterfilm.afterfilm.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.afterfilm.afterfilm.domain.User.Authority;
import org.afterfilm.afterfilm.domain.User.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {


    @NotNull
    @Size(min = 5, max = 100)
    private String email;

    @NotNull
    @Size(min = 2, max = 50)
    private String password;

    @NotNull
    @Size(min = 1, max = 20)
    private String nickname;

    private String gender;

    private int age;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .gender(gender)
                .age(age)
                .userImage("1")
                .useFlag("AC")
                .authority(Authority.ROLE_USER)
                .build();
    }

}
