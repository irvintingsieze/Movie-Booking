package com.sbooking.movie.controller;

import com.sbooking.movie.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @Autowired
    EmailService emailService;

    @Value("${email}")
    private String emails;
    @GetMapping("/hi")
    public String aes(){

        return emails;
    }
}
