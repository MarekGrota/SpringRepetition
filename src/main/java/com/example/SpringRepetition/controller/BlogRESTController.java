package com.example.SpringRepetition.controller;

import com.example.SpringRepetition.model.Category;
import com.example.SpringRepetition.model.Post;
import com.example.SpringRepetition.model.User;
import com.example.SpringRepetition.service.PostService;
import com.example.SpringRepetition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class BlogRESTController {

    UserService userService;
    PostService postService;

    @Autowired
    public BlogRESTController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @PostMapping("/user/register")
    public void registerUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        User user = new User(email, password, LocalDateTime.now(), false);
        userService.registerUser(user);
    }

    @PutMapping("/user/registerConfirm")
    public void registerConfirm(@RequestParam("userId") long userId) {
        userService.activateUser(userId);
    }

    @DeleteMapping("/user/delete")
    public void deleteUser(@RequestParam("userId") long userId) {
        try {
            userService.deleteUser(userId);
        } catch (EmptyResultDataAccessException e) {
        }
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsersOrderedByRegistrationDateTimeDesc();
    }

    @GetMapping("/user/email={email}")
    public User getUserByEmail(@RequestParam("email") String email) {
        return userService.getUserByEmail(email).orElse(new User());
    }

    @PostMapping("/post/addPost")
    public void addPost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("category") Category category,
            @RequestParam("userId") long userId
    ) {
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            if (userOptional.get().isStatus()) {
                postService.addPost(title, content, category, userOptional.get());
            }
        }
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/posts/byCategory")
    public List<Post> getPostsByCategory(
            @RequestParam("category") Category category) {
        return postService.getPostsByCategory(category);
    }

    @GetMapping("/posts/byCategoryAndAuthor")
    public List<Post> getPostsByCategoryAndAuthor(
            @RequestParam("category") Category category,
            @RequestParam("userId") long userId
    ) {
        if (userService.getUserById(userId).isPresent()) {
            return postService.getPostsByCategoryAndAuthor(category, userService.getUserById(userId).get());
        }
        return new ArrayList<>();
    }

    @GetMapping("/posts/keyWordsSearch")
    public List<Post> getPostsByTitleLikeOrContentLike(String keyWords){
        Set<Post> postSet = new HashSet<>();
        for (String keyWord : keyWords.split(",")) {
            postSet.addAll(postService.getPostsByTitleLikeOrContentLike(keyWord));
        }
        List<Post> filteredList = new ArrayList<>();
        filteredList.addAll(postSet);
        return filteredList;
    }
}