package com.application.ecommerce.controllers;

import com.application.ecommerce.entities.Post;
import com.application.ecommerce.services.PostService;
import com.application.ecommerce.services.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    UsersService usersService;
    @Autowired
    PostService postService;

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @PostMapping("/create/post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) throws Exception {
        post.setCreatedAt(LocalDateTime.now());
        Post savedPost = postService.createPost(post);
        return new ResponseEntity(savedPost, HttpStatus.OK);
    }

    @PutMapping("/edit/post")
    public ResponseEntity<Post> editPost(@RequestBody Post post, @RequestParam long id) throws Exception {
        post.setId(id);
        Post updatedPost = postService.editPost(post);
        return new ResponseEntity(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/delete/post")
    public ResponseEntity<Post> deletePost(@RequestParam(name = "id") long id, @RequestBody Post post) throws Exception {
        postService.deletePost(id, post);
        return new ResponseEntity("Status-i u ndryshua me sukses.", HttpStatus.OK);
    }

    @PutMapping("/change/post/status")
    public ResponseEntity<Post> changePostStatus(@RequestParam long id, @RequestBody Post post) throws Exception {
        Post updatedPostStatus = postService.changePostStatus(id, post);
        return new ResponseEntity(updatedPostStatus, HttpStatus.OK);
    }

    @GetMapping ("/search/post")
    public ResponseEntity<Post> searchPost(@RequestParam("name") String name) throws Exception {
        List<Post> searchedPost = postService.searchPost(name);
        return new ResponseEntity(searchedPost, HttpStatus.OK);
    }

    @GetMapping("/get/posts")
    public ResponseEntity<Post> getAllPosts() {
        List<Post> listOfPosts = postService.findAll();
        return new ResponseEntity(listOfPosts, HttpStatus.OK);
    }

}
