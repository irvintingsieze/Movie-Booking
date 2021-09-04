package com.sbooking.movie.repository;

import com.sbooking.movie.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User extends JpaRepository<Users, Integer> {
}
