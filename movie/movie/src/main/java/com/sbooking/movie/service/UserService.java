package com.sbooking.movie.service;

import com.sbooking.movie.dto.NewUser;
import com.sbooking.movie.model.Users;
import com.sbooking.movie.repository.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    User userRepository;

    public Users createNewUser(NewUser newUser) {
        return userRepository.save(newUser.getUser());
    }

    public Users getUser (String username, String password) {return userRepository.login(username,password);}

}
