package com.application.ecommerce.services;

import com.application.ecommerce.entities.Post;

import java.util.List;

public interface PostServiceImpl {

    List<Post> searchPost(String message);
}