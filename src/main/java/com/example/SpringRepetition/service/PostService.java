package com.example.SpringRepetition.service;

import com.example.SpringRepetition.model.Category;
import com.example.SpringRepetition.model.Post;
import com.example.SpringRepetition.model.User;
import com.example.SpringRepetition.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public void addPost(String title, String content, Category category, User author){
        postRepository.save(new Post(title,content, LocalDateTime.now(),category, author));
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll(Sort.by(Sort.Direction.DESC,"dateAdded"));
    }

    public List<Post> getPostsByCategory(Category category){
      return postRepository.findAllByCategory(category, Sort.by(Sort.Direction.DESC, "dateAdded"));
    }

    public List<Post> getPostsByCategoryAndAuthor(Category category, User author){
        return postRepository.findAllByCategoryAndAuthor(category, author, Sort.by(Sort.Direction.DESC, "dateAdded"));
    }

}
