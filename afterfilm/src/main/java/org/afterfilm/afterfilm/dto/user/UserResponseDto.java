package org.afterfilm.afterfilm.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.afterfilm.afterfilm.domain.User.User;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long userId;
    private String nickname;
    private String img;
    private String email;

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getUserId(), user.getNickname(), user.getUserImage(), user.getEmail());
    }
}
