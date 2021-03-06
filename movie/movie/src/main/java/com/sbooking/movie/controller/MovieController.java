package com.sbooking.movie.controller;

import com.sbooking.movie.model.Movie;
import com.sbooking.movie.model.MovieSession;
import com.sbooking.movie.service.MovieService;
import com.sbooking.movie.service.MovieSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;
    @Autowired
    MovieSessionService movieSessionService;

    @GetMapping("/movie/findAllMovies")
    public List<Movie> findAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("/movie/{id}")
    public Movie findMovieById(@PathVariable String id){
            return movieService.getMovieById(Integer.parseInt(id));
    }

    @GetMapping("moviesession/{id}")
    public MovieSession findMovieSessionById(@PathVariable String id){
        try{
            return movieSessionService.getMovieSessionById(Integer.parseInt(id));
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping("moviesession/movie/{mv_id}")
    public List<MovieSession> findMovieSessionByMovieId(@PathVariable String mv_id){
        try{
            return movieSessionService.getAllMovieSessionByMovieId(Integer.parseInt(mv_id));
        }catch (Exception e){
            return null;
        }
    }

}
