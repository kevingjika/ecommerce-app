package com.application.ecommerce.controllers;

import com.application.ecommerce.entities.Users;
import com.application.ecommerce.services.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class UsersController {

    @Autowired
    UsersService usersService;

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    @PostMapping("/create/user")
    public ResponseEntity<Users> createUser(@RequestBody Users users) throws Exception {
        log.info(users.getFirstName());
        log.info(users.getRole().name());
        users.setCreatedAt(LocalDateTime.now());
        Users savedUser = usersService.createUser(users);
        return new ResponseEntity(savedUser, HttpStatus.OK);
    }

    @PutMapping("/edit/user")
    public ResponseEntity<Users> editUser(@RequestBody Users users, @RequestParam long id) throws Exception {
        users.setId(id);
        Users updatedUser = usersService.createUser(users);
        return new ResponseEntity(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/user")
    public ResponseEntity<Users> deleteUser(@RequestParam(name = "id") long id) throws Exception {
        usersService.deleteUser(id);
        return new ResponseEntity("User-i u fshi me sukses.", HttpStatus.OK);
    }
}
