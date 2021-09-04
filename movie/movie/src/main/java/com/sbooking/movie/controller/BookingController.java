package com.sbooking.movie.controller;

import com.sbooking.movie.model.Booking;
import com.sbooking.movie.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("booking/newBooking/{user_id}/{movie_seating_id}")
    public Booking addBooking (@PathVariable Map<String, String> bookingMap){
        try{
            int userid = Integer.parseInt(bookingMap.get("user_id"));
            int movieSeatingId = Integer.parseInt(bookingMap.get("movie_seating_id"));
            return bookingService.addNewBooking(userid,movieSeatingId);
        }catch (RuntimeException e){
            return null;
        }
    }

    @GetMapping("/booking/{user_id}")
    public List<Booking> getBookingsByUser (@PathVariable String user_id){
        try{
            return bookingService.getBookingsByUser(Integer.parseInt(user_id));
        }catch (RuntimeException e){
            return null;
        }
    }
}
