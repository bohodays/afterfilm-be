package org.afterfilm.afterfilm.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieStillsCutDto {

    private String movieId;
    private String stillsUrl;
}
