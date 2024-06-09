package org.afterfilm.afterfilm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.afterfilm.afterfilm.domain.MovieDetail;
import org.afterfilm.afterfilm.dto.*;
import org.afterfilm.afterfilm.repository.MovieDetailMapper;
import org.afterfilm.afterfilm.repository.MovieInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieDetailService {

    private final MovieDetailMapper movieDetailMapper;
    private final MovieInfoService movieInfoService;

    public MovieDetailDto getMovieDetail(String docId) {
        MovieDetailDto movieDetailInfo = movieDetailMapper.getMovieDetail(docId).toDto();
        List<MovieStaffDto> movieStaff = movieInfoService.getMovieStaff(docId);
        List<MovieVideoDto> movieVideo = movieInfoService.getMovieVideo(docId);
        List<MovieStillsCutDto> movieStillsCut = movieInfoService.getMovieStillsCut(docId);

        movieDetailInfo.setStaff(movieStaff);
        movieDetailInfo.setVideos(movieVideo);
        movieDetailInfo.setStillsCut(movieStillsCut);

        return movieDetailInfo;
    }
}
