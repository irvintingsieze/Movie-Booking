package com.sbooking.movie.service;

import com.sbooking.movie.model.Movie;
import com.sbooking.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public List<Movie> getAllMovies (){
        return movieRepository.findAll();
    }

    public Movie getMovieById(int id){
        return movieRepository.findById(id).orElse(null);
    }
}
