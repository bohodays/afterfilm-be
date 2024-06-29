package org.afterfilm.afterfilm.repository;

import org.afterfilm.afterfilm.domain.movie.MovieStaff;
import org.afterfilm.afterfilm.domain.movie.MovieStillsCut;
import org.afterfilm.afterfilm.domain.movie.MovieVideo;
import org.afterfilm.afterfilm.dto.movie.MovieStaffDto;
import org.afterfilm.afterfilm.dto.movie.MovieVideoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MovieInfoMapper {

    List<MovieStaff> getMovieStaff(String docId);

    List<MovieVideo> getMovieVideo(String docId);

    List<MovieStillsCut> getMovieStillsCut(String docId);
}
