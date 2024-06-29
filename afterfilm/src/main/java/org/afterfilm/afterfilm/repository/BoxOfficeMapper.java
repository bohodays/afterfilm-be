package org.afterfilm.afterfilm.repository;

import org.afterfilm.afterfilm.domain.movie.BoxOffice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoxOfficeMapper {

    List<BoxOffice> getBoxOffice();
}
