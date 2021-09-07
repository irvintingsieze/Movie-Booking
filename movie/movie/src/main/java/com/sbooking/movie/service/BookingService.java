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

    public void addNewBooking (int userid, int[] bookedSeatId){
        Users newUser = userRepository.findById(userid).orElse(null);
        for (int i = 0; i<bookedSeatId.length;i++) {
            MovieSeating movieSeating = movieSeatingRepository.findById(bookedSeatId[i]).orElse(null);
            Booking newBooking = new Booking();
            newBooking.setUsers(newUser);
            newBooking.setMovieSeating(movieSeating);
            bookingRepository.save(newBooking);
        }
    }

    public List<Booking> getBookingsByUser (int userid){
        return bookingRepository.findAllByUsers(userid);
    }
}
