package com.application.ecommerce.controllers;

import com.application.ecommerce.entities.Favourites;
import com.application.ecommerce.entities.Post;
import com.application.ecommerce.entities.Users;
import com.application.ecommerce.services.FavouritesService;
import com.application.ecommerce.services.PostService;
import com.application.ecommerce.services.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PreRemove;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/favourite")
public class FavouritesController {

    @Autowired
    UsersService usersService;
    @Autowired
    PostService postService;
    @Autowired
    FavouritesService favouritesService;

    private static final Logger log = LoggerFactory.getLogger(FavouritesController.class);

    @PostMapping("/create")
    public ResponseEntity<Favourites> createFavourite(@RequestBody Favourites favourites) throws Exception {
        Favourites savedFavourite = favouritesService.createFavourite(favourites);
        return new ResponseEntity(savedFavourite, HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity removeFavourite(@RequestParam(name = "id") long id) throws Exception {
        favouritesService.removeFavourite(id);
        return new ResponseEntity("Remove u krye me sukses.", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Favourites> deleteFavourite(@RequestParam(name = "id") long id) throws Exception {
        favouritesService.deleteFavourite(id);
        return new ResponseEntity("Favourite u fshi me sukses.", HttpStatus.OK);
    }

    @GetMapping ("/search")
    public ResponseEntity<Favourites> searchFavourite(@RequestParam("name") String name) throws Exception {
        List<Favourites> searchedFavourite = favouritesService.searchFavourite(name);
        return new ResponseEntity(searchedFavourite, HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<Favourites> getAllFavourites() {
        List<Favourites> listOfFavourites = favouritesService.findAll();
        return new ResponseEntity(listOfFavourites, HttpStatus.OK);
    }

}
