package com.sbooking.movie.service;

import com.sbooking.movie.model.Movie;
import com.sbooking.movie.model.MovieSession;
import com.sbooking.movie.repository.MovieRepository;
import com.sbooking.movie.repository.MovieSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieSessionService {

    @Autowired
    MovieSessionRepository movieSessionRepository;

    public MovieSession getMovieSessionById(int id){
        return movieSessionRepository.findById(id).orElse(null);
    }

    public List<MovieSession> getAllMovieSessionByMovieId (int movieId) {
        return movieSessionRepository.findAllByMovieId(movieId);
    }
}
