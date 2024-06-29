package org.afterfilm.afterfilm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.afterfilm.afterfilm.dto.movie.MovieStaffDto;
import org.afterfilm.afterfilm.dto.movie.MovieStillsCutDto;
import org.afterfilm.afterfilm.dto.movie.MovieVideoDto;
import org.afterfilm.afterfilm.repository.MovieInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieInfoService {

    private final MovieInfoMapper movieInfoMapper;

    public List<MovieStaffDto> getMovieStaff(String docId) {
        return movieInfoMapper.getMovieStaff(docId)
                .stream()
                .map(item -> item.toDto())
                .toList();
    }

    public List<MovieVideoDto> getMovieVideo(String docId) {
        return movieInfoMapper.getMovieVideo(docId)
                .stream()
                .map(item -> item.toDto())
                .toList();
    }

    public List<MovieStillsCutDto> getMovieStillsCut(String docId) {
        return movieInfoMapper.getMovieStillsCut(docId)
                .stream()
                .map(item -> item.toDto())
                .toList();
    }
}
