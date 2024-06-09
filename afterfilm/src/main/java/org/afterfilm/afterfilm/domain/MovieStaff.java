package org.afterfilm.afterfilm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.afterfilm.afterfilm.dto.MovieStaffDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieStaff {

    private String movieId;
    private String staffId;
    private String staffNm;
    private String staffEnNm;
    private String staffRole;

    public MovieStaffDto toDto() {
        return MovieStaffDto.builder()
                .movieId(movieId)
                .staffId(staffId)
                .staffNm(staffNm)
                .staffEnNm(staffEnNm)
                .staffRole(staffRole)
                .build();
    }
}
