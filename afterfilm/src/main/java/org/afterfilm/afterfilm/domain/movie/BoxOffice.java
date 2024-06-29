package org.afterfilm.afterfilm.domain.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.afterfilm.afterfilm.dto.movie.BoxOfficeDto;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoxOffice {

    private String movieId;
    private String title;
    private String plot;
    private int rank;
    private String rlsDate;
    private int runtime;
    private List<String> genres;
    private List<String> posterUrls;

    public BoxOfficeDto toDto() {
        return BoxOfficeDto.builder()
                .movieId(movieId)
                .title(title)
                .plot(plot)
                .rank(rank)
                .rlsDate(rlsDate)
                .runtime(runtime)
                .genres(genres)
                .posterUrls(posterUrls)
                .build();
    }
}
