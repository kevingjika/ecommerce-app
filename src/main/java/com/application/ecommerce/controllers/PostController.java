package com.application.ecommerce.controllers;

import com.application.ecommerce.entities.Users;
import com.application.ecommerce.entities.Post;
import com.application.ecommerce.services.PostService;
import com.application.ecommerce.services.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post) throws Exception {
        log.info(post.getTitle());
        log.info(post.getDescription());
        log.info(post.getStatus() != null ? post.getStatus().name() : "failed");
        post.setCreatedAt(LocalDateTime.now());
        Post savedPost = postService.createPost(post);
        return new ResponseEntity(savedPost, HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<Post> editPost(@RequestBody Post post, @RequestParam long id) throws Exception {
        post.setId(id);
        Post updatedPost = postService.editPost(post);
        try {
            FileWriter myWriter = new FileWriter("javalogs.txt");
            myWriter.write(Long.toString(updatedPost.getId()));
            myWriter.close();
        } catch (IOException e) {
        }
        return new ResponseEntity(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Post> deletePost(@RequestParam(name = "id") long id) throws Exception {
        postService.deletePost(id);
        return new ResponseEntity("Vlera false e variablit delete u ndryshua ne true me sukses.", HttpStatus.OK);
    }

    @PutMapping("/change/status")
    public ResponseEntity<Post> changePostStatus(@RequestParam long id) throws Exception {
        Post updatedPostStatus = postService.changePostStatus(id);
        return new ResponseEntity(updatedPostStatus, HttpStatus.OK);
    }

    @GetMapping ("/search")
    public ResponseEntity<Post> searchPost(@RequestParam("name") String name) throws Exception {
        List<Post> searchedPost = postService.searchPost(name);
        return new ResponseEntity(searchedPost, HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<Post> getAllPosts() {
        List<Post> listOfPosts = postService.findAll();
        return new ResponseEntity(listOfPosts, HttpStatus.OK);
    }

}
