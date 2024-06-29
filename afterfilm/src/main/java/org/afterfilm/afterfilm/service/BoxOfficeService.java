package org.afterfilm.afterfilm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.afterfilm.afterfilm.domain.movie.BoxOffice;
import org.afterfilm.afterfilm.dto.movie.BoxOfficeDto;
import org.afterfilm.afterfilm.repository.BoxOfficeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoxOfficeService {

    private final BoxOfficeMapper boxOfficeMapper;

    public List<BoxOfficeDto> getBoxOffice() {
        return boxOfficeMapper.getBoxOffice()
                .stream()
                .map(item -> item.toDto())
                .toList();
    }
}
