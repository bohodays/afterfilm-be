package org.afterfilm.afterfilm.dto.movie;

import lombok.Builder;
import lombok.Data;
import org.afterfilm.afterfilm.dto.movie.MovieStaffDto;
import org.afterfilm.afterfilm.dto.movie.MovieStillsCutDto;
import org.afterfilm.afterfilm.dto.movie.MovieVideoDto;

import java.util.List;

@Data
@Builder
public class MovieDetailDto {

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
}
