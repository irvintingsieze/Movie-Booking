package com.sbooking.movie.repository;

import com.sbooking.movie.model.Seats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatsRepository extends JpaRepository<Seats,Integer> {
}
