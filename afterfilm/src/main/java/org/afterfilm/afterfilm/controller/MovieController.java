package org.afterfilm.afterfilm.controller;

import lombok.extern.slf4j.Slf4j;
import org.afterfilm.afterfilm.domain.BoxOffice;
import org.afterfilm.afterfilm.dto.BoxOfficeDto;
import org.afterfilm.afterfilm.exception.error.ErrorCode;
import org.afterfilm.afterfilm.response.ErrorResponse;
import org.afterfilm.afterfilm.response.SuccessResponse;
import org.afterfilm.afterfilm.service.BoxOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private BoxOfficeService boxOfficeService;

    @GetMapping("/box-office")
    public ResponseEntity<?> getBoxOffice() {
        try {
            List<BoxOfficeDto> boxOffice = boxOfficeService.getBoxOffice();
            return new ResponseEntity<>(new SuccessResponse(boxOffice), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
