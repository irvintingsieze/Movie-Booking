package com.sbooking.movie.service;

import com.sbooking.movie.model.Booking;
import com.sbooking.movie.model.MovieSeating;
import com.sbooking.movie.model.Users;
import com.sbooking.movie.repository.BookingRepository;
import com.sbooking.movie.repository.MovieSeatingRepository;
import com.sbooking.movie.repository.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    User userRepository;
    @Autowired
    MovieSeatingRepository movieSeatingRepository;
    @Autowired
    BookingRepository bookingRepository;

    public Booking addNewBooking (int userid, int movieSeatingId){
        Users newUser = userRepository.findById(userid).orElse(null);
        MovieSeating movieSeating = movieSeatingRepository.findById(movieSeatingId).orElse(null);
        Booking newBooking = new Booking();
        newBooking.setUsers(newUser);
        newBooking.setMovieSeating(movieSeating);
        return bookingRepository.save(newBooking);
    }

    public List<Booking> getBookingsByUser (int userid){
        return bookingRepository.findAllByUsers(userid);
    }
}
