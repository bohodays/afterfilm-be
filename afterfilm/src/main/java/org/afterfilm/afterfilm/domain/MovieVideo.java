package org.afterfilm.afterfilm.domain;

import org.afterfilm.afterfilm.dto.MovieDetailDto;
import org.afterfilm.afterfilm.dto.MovieVideoDto;

public class MovieVideo {

    private String movieId;
    private String title;
    private String vodUrls;

    public MovieVideoDto toDto() {
        return MovieVideoDto.builder()
                .movieId(movieId)
                .title(title)
                .vodUrls(vodUrls)
                .build();
    }
}
