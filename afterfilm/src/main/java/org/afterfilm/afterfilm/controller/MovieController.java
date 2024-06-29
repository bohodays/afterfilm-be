package org.afterfilm.afterfilm.controller;

import lombok.extern.slf4j.Slf4j;
import org.afterfilm.afterfilm.domain.movie.BoxOffice;
import org.afterfilm.afterfilm.dto.movie.BoxOfficeDto;
import org.afterfilm.afterfilm.dto.movie.MovieDetailDto;
import org.afterfilm.afterfilm.exception.error.ErrorCode;
import org.afterfilm.afterfilm.response.ErrorResponse;
import org.afterfilm.afterfilm.response.SuccessResponse;
import org.afterfilm.afterfilm.service.BoxOfficeService;
import org.afterfilm.afterfilm.service.MovieDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private BoxOfficeService boxOfficeService;
    @Autowired
    private MovieDetailService movieDetailService;

    @GetMapping("/box-office")
    public ResponseEntity<?> getBoxOffice() {
        try {
            List<BoxOfficeDto> boxOffice = boxOfficeService.getBoxOffice();
            return new ResponseEntity<>(new SuccessResponse(boxOffice), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{docId}")
    public ResponseEntity<?> getMovieDetail(@PathVariable String docId) {
        try {
            MovieDetailDto movieDetail = movieDetailService.getMovieDetail(docId);
            return new ResponseEntity<>(new SuccessResponse(movieDetail), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
