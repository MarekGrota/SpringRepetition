package com.example.SpringRepetition.service;

import com.example.SpringRepetition.model.User;
import com.example.SpringRepetition.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<User> getAllUsersOrderedByRegistrationDateTimeDesc(){
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "registrationDateTime"));
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.findFirstByEmail(email);
    }

    public Optional<User> getUserById(long userId){
        return userRepository.findById(userId);
    }
}
