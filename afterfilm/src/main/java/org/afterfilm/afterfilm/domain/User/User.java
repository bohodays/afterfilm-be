package org.afterfilm.afterfilm.domain.User;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "USER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name="EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NICKNAME", length = 20, nullable = false)
    private String nickname;

    @Column(name="GENDER")
    private String gender;

    @Column(name="AGE")
    private int age;

    @Column(name="USER_IMAGE")
    private String userImage;

    @Column(name="USE_FLAG")
    private String useFlag;

    @Column(name="RF_TOKEN")
    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    public User updateToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public User updateUser(String nickname){
        this.nickname = nickname;
        return this;
    }

    public User updateImg(String img){
        this.userImage = img;
        return this;
    }

    public User updatePassword(String password, PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
        return this;
    }
}
