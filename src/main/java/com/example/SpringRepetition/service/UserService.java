package com.example.SpringRepetition.service;

import com.example.SpringRepetition.model.User;
import com.example.SpringRepetition.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void registerUser(User user){
        userRepository.save(user);
    }

    public void activateUser(long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            user.setStatus(true);
            userRepository.save(user);
        }
    }

    public void deleteUser(long userId){
        userRepository.deleteById(userId);
    }
}
