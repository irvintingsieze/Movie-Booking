package com.sbooking.movie.controller;

import com.sbooking.movie.dto.EmailMessage;
import com.sbooking.movie.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class EmailBookingController {

    @Autowired
    EmailService emailService;

    @PostMapping("/booking/email_confirmation")
    public ResponseEntity sendingEmail(@RequestBody EmailMessage emailMessage){
        try{
            if (!emailService.checkValidEmail(emailMessage.getEmail())){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            emailService.sendMail(emailMessage);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }


}
