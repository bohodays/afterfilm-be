package org.afterfilm.afterfilm.dto.movie;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MovieVideoDto {

    private String movieId;
    private String title;
    private String vodUrls;
}
