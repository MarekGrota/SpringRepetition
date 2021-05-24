package com.example.SpringRepetition.controller;

import com.example.SpringRepetition.model.User;
import com.example.SpringRepetition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class BlogRESTController {

    @Autowired
    UserService userService;

    @PostMapping("/user/register")
    public void registerUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ){
        User user = new User(email,password,LocalDateTime.now(),false);
        userService.registerUser(user);
    }
}
