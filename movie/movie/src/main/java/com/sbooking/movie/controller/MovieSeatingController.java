package com.sbooking.movie.controller;

import com.sbooking.movie.model.MovieSeating;
import com.sbooking.movie.service.MovieSeatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieSeatingController {

    @Autowired
    MovieSeatingService movieSeatingService;

    @PostMapping("movie_seating/new_session/{id}")
    public List<MovieSeating> addNewSession(@PathVariable String id){
        try{
            return movieSeatingService.addNewSession(Integer.parseInt(id));
        }catch (Exception e){

            return null;
        }
    }

    @PatchMapping("movie_seating/{id}")
    public MovieSeating setOccupied(@PathVariable String id){
        try{
            return movieSeatingService.setOccupied(Integer.parseInt(id));
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping("movie_seating/getSeats/{id}")
    public List<MovieSeating> getSeatsBySession(@PathVariable String id){
        try{
            return movieSeatingService.getSeatsBySession(Integer.parseInt(id));
        }catch (Exception e){
            return null;
        }
    };
}
