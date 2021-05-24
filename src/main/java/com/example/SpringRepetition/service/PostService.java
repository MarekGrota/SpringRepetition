package com.example.SpringRepetition.service;

import com.example.SpringRepetition.model.Category;
import com.example.SpringRepetition.model.Post;
import com.example.SpringRepetition.model.User;
import com.example.SpringRepetition.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public void addPost(String title, String content, User author, Category category){
        postRepository.save(new Post(title,content, LocalDateTime.now(),category, author));
    }
}
