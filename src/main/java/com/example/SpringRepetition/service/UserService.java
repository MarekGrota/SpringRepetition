package com.example.SpringRepetition.service;

import com.example.SpringRepetition.model.User;
import com.example.SpringRepetition.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void registerUser(User user){
        userRepository.save(user);
    }
}
