package com.application.ecommerce.controllers;

import com.application.ecommerce.entities.Users;
import com.application.ecommerce.services.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    @PostMapping("/create")
    public ResponseEntity<Users> createUser(@RequestBody Users users) throws Exception {
        log.info(users.getUsername());
        log.info(users.getFirstName());
        log.info(users.getLastName());
        log.info(users.getRole() != null ? users.getRole().name() : "failed");
        users.setCreatedAt(LocalDateTime.now());
        Users savedUser = usersService.createUser(users);
        return new ResponseEntity(savedUser, HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<Users> editUser(@RequestBody Users users, @RequestParam long id) throws Exception {
        users.setId(id);
        Users updatedUser = usersService.createUser(users);
        return new ResponseEntity(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Users> deleteUser(@RequestParam(name = "id") long id) throws Exception {
        usersService.deleteUser(id);
        return new ResponseEntity("User-i u fshi me sukses.", HttpStatus.OK);
    }

    @GetMapping("/register")
    public String showForm(Model model) {
        Users users = new Users();
        model.addAttribute("users", users);
        return "register_form";
    }

    @PostMapping("/register")
    public String submitForm(@ModelAttribute("users") Users users) {
        System.out.println(users);
        return "register_success";
    }
}
