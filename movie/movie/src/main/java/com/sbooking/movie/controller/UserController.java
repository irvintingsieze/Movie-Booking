package com.sbooking.movie.controller;

import com.sbooking.movie.dto.NewUser;
import com.sbooking.movie.model.Users;
import com.sbooking.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("user")
    public Users createNewUser (@RequestBody NewUser newUser){
        return userService.createNewUser(newUser);
    }
}
