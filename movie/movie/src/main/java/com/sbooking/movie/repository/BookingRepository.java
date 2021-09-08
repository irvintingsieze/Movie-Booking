package com.sbooking.movie.repository;

import com.sbooking.movie.model.Booking;
import com.sbooking.movie.model.MovieSeating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    @Query(value = "select * from booking where user_id = ?", nativeQuery = true)
    List<Booking> findAllByUsers(int userid);
}
