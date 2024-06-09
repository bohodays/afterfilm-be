package org.afterfilm.afterfilm.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MovieStaffDto {

    private String movieId;
    private String staffId;
    private String staffNm;
    private String staffEnNm;
    private String staffRole;
}
