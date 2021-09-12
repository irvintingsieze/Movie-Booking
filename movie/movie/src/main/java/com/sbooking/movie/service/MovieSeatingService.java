package com.sbooking.movie.service;

import com.sbooking.movie.model.MovieSeating;
import com.sbooking.movie.model.MovieSession;
import com.sbooking.movie.model.Seats;
import com.sbooking.movie.repository.MovieSeatingRepository;
import com.sbooking.movie.repository.MovieSessionRepository;
import com.sbooking.movie.repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieSeatingService {

    @Autowired
    MovieSeatingRepository movieSeatingRepository;
    @Autowired
    SeatsRepository seatsRepository;
    @Autowired
    MovieSessionRepository movieSessionRepository;

    public final int MOVIE_SEATS_TOTAL = 50;

    public List<MovieSeating> addNewSession (int movieSessionId){
        List<MovieSeating> movieSeatings = new ArrayList<>();
        MovieSession movieSession = movieSessionRepository.findById(movieSessionId).orElse(null);
        Seats vipSeats = seatsRepository.findById(1).orElse(null);
        Seats normalSeats = seatsRepository.findById(2).orElse(null);
        for (int index = 0; index<MOVIE_SEATS_TOTAL; index ++){
            MovieSeating movieSeating = new MovieSeating();
            movieSeating.setOccupied(false);
            movieSeating.setSelected(false);
            movieSeating.setMovieSessions(movieSession);
            if (index>=30 || index < 20)
                movieSeating.setSeats(normalSeats);
            else
                movieSeating.setSeats(vipSeats);
            movieSeatings.add(movieSeatingRepository.save(movieSeating));
        }
        return movieSeatings;
    }

    public MovieSeating setOccupied (int movieSeatingId){
        MovieSeating movieSeating = movieSeatingRepository.findById(movieSeatingId).orElse(null);
        if (movieSeating!=null){
            movieSeating.setOccupied(true);
            return movieSeatingRepository.save(movieSeating);
        }
        return null;
    }

    public List<MovieSeating> getSeatsBySession (int sessionId){
        return movieSeatingRepository.findAllByMovieSession(sessionId);
    }

    public void setMultipleOccupied (int[] seatIdList){
        List<MovieSeating> movieSeatingList = new ArrayList<>();
        for (int i=0; i<seatIdList.length; i++){
            MovieSeating movieSeating = movieSeatingRepository.findById(seatIdList[i]).orElse(null);
            if (movieSeating!=null){
                movieSeating.setOccupied(true);
                movieSeatingList.add(movieSeating);
            }
        }
        movieSeatingRepository.saveAll(movieSeatingList);
    }

}
