package com.sbooking.movie.repository;

import com.sbooking.movie.model.MovieSession;
import com.sbooking.movie.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface User extends JpaRepository<Users, Integer> {
    @Query(value = "SELECT * FROM users WHERE username = ? AND password = ?", nativeQuery = true)
    Users login(String username, String password);
}
