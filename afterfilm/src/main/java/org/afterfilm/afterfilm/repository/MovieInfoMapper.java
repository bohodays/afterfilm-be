package org.afterfilm.afterfilm.repository;

import org.afterfilm.afterfilm.domain.MovieStaff;
import org.afterfilm.afterfilm.domain.MovieStillsCut;
import org.afterfilm.afterfilm.domain.MovieVideo;
import org.afterfilm.afterfilm.dto.MovieStaffDto;
import org.afterfilm.afterfilm.dto.MovieVideoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MovieInfoMapper {

    List<MovieStaff> getMovieStaff(String docId);

    List<MovieVideo> getMovieVideo(String docId);

    List<MovieStillsCut> getMovieStillsCut(String docId);
}
