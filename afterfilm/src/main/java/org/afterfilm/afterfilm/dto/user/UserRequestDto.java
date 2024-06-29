package org.afterfilm.afterfilm.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    @NotNull
    @Size(min = 1, max = 20)
    private String nickname;

    private String userImage;

    @NotNull
    @Size(min = 5, max = 100)
    private String email;
}
