package com.sbooking.movie.controller;

import com.sbooking.movie.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @Autowired
    EmailService emailService;

    @GetMapping("/hi")
    public String aes(){
        return "HI";
    }
}
