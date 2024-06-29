package org.afterfilm.afterfilm.domain.movie;

import org.afterfilm.afterfilm.dto.movie.MovieDetailDto;
import org.afterfilm.afterfilm.dto.movie.MovieVideoDto;

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
