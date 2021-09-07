package com.sbooking.movie.repository;

import com.sbooking.movie.model.MovieSeating;
import com.sbooking.movie.model.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieSessionRepository extends JpaRepository<MovieSession, Integer> {
    @Query(value = "SELECT * FROM movie_session WHERE mv_id = ?", nativeQuery = true)
    List<MovieSession> findAllByMovieId(int movieId);
}
