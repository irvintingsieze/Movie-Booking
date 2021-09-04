package com.sbooking.movie.repository;

import com.sbooking.movie.model.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieSessionRepository extends JpaRepository<MovieSession, Integer> {
}
