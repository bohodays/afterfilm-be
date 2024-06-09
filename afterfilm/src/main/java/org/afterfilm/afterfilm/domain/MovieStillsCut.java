package org.afterfilm.afterfilm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.afterfilm.afterfilm.dto.MovieStillsCutDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieStillsCut {

    private String movieId;
    private String stillsUrl;

    public MovieStillsCutDto toDto() {
        return MovieStillsCutDto.builder()
                .movieId(movieId)
                .stillsUrl(stillsUrl)
                .build();
    }
}
