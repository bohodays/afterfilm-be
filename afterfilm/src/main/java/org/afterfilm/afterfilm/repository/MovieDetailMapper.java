package org.afterfilm.afterfilm.repository;

import org.afterfilm.afterfilm.domain.movie.MovieDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MovieDetailMapper {

    MovieDetail getMovieDetail(String docId);
}
