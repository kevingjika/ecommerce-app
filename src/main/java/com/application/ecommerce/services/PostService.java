package com.application.ecommerce.services;

import com.application.ecommerce.entities.Post;
import com.application.ecommerce.entities.Users;
import com.application.ecommerce.repository.PostRepository;
import com.application.ecommerce.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements PostServiceImpl{

    PostRepository postRepository;
    UsersRepository usersRepository;

    PostService(PostRepository postRepository, UsersRepository usersRepository){
        this.postRepository = postRepository;
        this.usersRepository = usersRepository;
    }

    public Post createPost(Post post) throws Exception{
        post.setStatus(Post.Status.PENDING);
        post.setDeleted(false);
        Optional<Users> findIfUsersExists = usersRepository.findById(post.getUsers().getId());
        if (!findIfUsersExists.isPresent()){
            throw new Exception("User-i qe kerkoni nuk ekziston.");
        } else {
            post.setUsers(findIfUsersExists.get());
            findIfUsersExists.get().setCreatedAt(post.getCreatedAt());
        }
        return postRepository.save(post);
    }

    public Post editPost(Post post) throws Exception {
        post.setStatus(Post.Status.PENDING);
        post.setDeleted(false);
        Optional<Post> findIfPostExists = postRepository.findById(post.getId());
        if(!findIfPostExists.isPresent()) {
            throw new Exception("Post-i nuk ekziston.");
        }
        return postRepository.save(findIfPostExists.get());
    }

    public void deletePost(long id, Post post) {
        post.setDeleted(true);
    }

    public Post changePostStatus(long id, Post post) throws Exception{
        post.setDeleted(false);
        Optional<Post> findIfPostExists = postRepository.findById(id);
        if(!findIfPostExists.get().equals(Users.Role.ADMIN.name())) {
            throw new Exception ("Statusi i post-it nuk do te ndryshohet.");
        }
        findIfPostExists.get().setStatus(Post.Status.APPROVED);
        return postRepository.save(findIfPostExists.get());
    }

    @Override
    public List<Post> searchPost(String message) {
        List<Post> findIfPostExists = postRepository.searchSpidSQL(message);
        if(!findIfPostExists.isEmpty()) {
            return findIfPostExists;
        }
        return postRepository.findAll();
    }

    public List <Post> findAll() {
        return postRepository.findAll();
    }





}
