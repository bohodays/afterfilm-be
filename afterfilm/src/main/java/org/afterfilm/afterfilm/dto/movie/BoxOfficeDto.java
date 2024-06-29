package org.afterfilm.afterfilm.dto.movie;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BoxOfficeDto {

    private String movieId;
    private String title;
    private String plot;
    private int rank;
    private String rlsDate;
    private int runtime;
    private List<String> genres;
    private List<String> posterUrls;
}
