package com.sbooking.movie.controller;

import com.sbooking.movie.dto.MovieSeatUpdate;
import com.sbooking.movie.model.Booking;
import com.sbooking.movie.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("booking/newBooking/{user_id}")
    public void addBooking (@PathVariable String user_id, @RequestBody MovieSeatUpdate bookedSeats){
        try{
            int userid = Integer.parseInt(user_id);
            bookingService.addNewBooking(userid,bookedSeats.getSeatList());
        }catch (RuntimeException e){
            System.out.println(e);
        }
    }

    @GetMapping("/booking")
    public List<Booking> getBookingsByUser (@RequestParam String user_id){
        try{
            return bookingService.getBookingsByUser(Integer.parseInt(user_id));
        }catch (RuntimeException e){
            return null;
        }
    }
}
