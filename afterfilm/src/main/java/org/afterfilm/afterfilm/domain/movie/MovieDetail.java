package org.afterfilm.afterfilm.domain.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.afterfilm.afterfilm.dto.movie.MovieDetailDto;
import org.afterfilm.afterfilm.dto.movie.MovieStaffDto;
import org.afterfilm.afterfilm.dto.movie.MovieStillsCutDto;
import org.afterfilm.afterfilm.dto.movie.MovieVideoDto;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetail {

    private String movieId;
    private String title;
    private String plot;
    private String rlsDate;
    private int runtime;
    private String rating;
    private String nation;
    private List<String> genres;
    private List<String> posterUrls;
    private List<MovieVideoDto> videos;
    private List<MovieStaffDto> staff;
    private List<MovieStillsCutDto> stillsCut;

    public MovieDetailDto toDto() {
        return MovieDetailDto.builder()
                .movieId(movieId)
                .title(title)
                .plot(plot)
                .rlsDate(rlsDate)
                .runtime(runtime)
                .rating(rating)
                .nation(nation)
                .genres(genres)
                .posterUrls(posterUrls)
                .build();
    }
}
