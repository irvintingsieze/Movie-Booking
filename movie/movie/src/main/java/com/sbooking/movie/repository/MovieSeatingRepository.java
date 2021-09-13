package com.sbooking.movie.repository;

import com.sbooking.movie.model.MovieSeating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieSeatingRepository extends JpaRepository<MovieSeating,Integer> {
    @Query(value = "SELECT * FROM movie_seating WHERE mv_session_id = ? ORDER BY movie_seating_id ASC", nativeQuery = true)
    List<MovieSeating> findAllByMovieSession(int movieSessionId);
}
