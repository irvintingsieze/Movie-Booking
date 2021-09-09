package com.sbooking.movie.controller;

import com.sbooking.movie.dto.MovieSeatUpdate;
import com.sbooking.movie.model.MovieSeating;
import com.sbooking.movie.model.Seats;
import com.sbooking.movie.service.MovieSeatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
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

    @PatchMapping("movie_seating/occupy")
    public void setMultipleOccupied(@RequestBody MovieSeatUpdate listSeatId){
        try{
            movieSeatingService.setMultipleOccupied(listSeatId.getSeatList());
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @GetMapping("movie_seating/getSeats")
    public List<MovieSeating> getSeatsBySession(@RequestParam String id){
        try{
            return movieSeatingService.getSeatsBySession(Integer.parseInt(id));
        }catch (Exception e){
            return null;
        }
    };

    @GetMapping("movie_seating/getAllSeats")
    public List<Seats> getAllSeats (){
        return movieSeatingService.getAllSeats();
    }

    @PatchMapping("movie_seating/{id}")
    public MovieSeating setSeatsSelected(@PathVariable String id){
        try{
            return movieSeatingService.setOccupied(Integer.parseInt(id));
        }catch (Exception e){
            return null;
        }
    }
}
