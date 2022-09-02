package com.application.ecommerce.services;

import com.application.ecommerce.entities.Post;
import com.application.ecommerce.entities.Users;
import com.application.ecommerce.entities.Favourites;
import com.application.ecommerce.repository.FavouritesRepository;
import com.application.ecommerce.repository.PostRepository;
import com.application.ecommerce.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.PreRemove;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FavouritesService implements FavouritesServiceImpl{

    PostRepository postRepository;
    UsersRepository usersRepository;
    FavouritesRepository favouritesRepository;

    FavouritesService(PostRepository postRepository, UsersRepository usersRepository, FavouritesRepository favouritesRepository){
        this.postRepository = postRepository;
        this.usersRepository = usersRepository;
        this.favouritesRepository = favouritesRepository;
    }
    @Transactional
    public void removeFavourite (long id) throws Exception {
        return;
    }
    public void deleteFavourite(long id) throws Exception {
        favouritesRepository.deleteById(id);
    }
    @Override
    public List<Favourites> searchFavourite(String message) {
        List<Favourites> findIfFavouriteExists = favouritesRepository.searchFavouriteSQL(message);
        if(!findIfFavouriteExists.isEmpty()) {
            return findIfFavouriteExists;
        }
        return favouritesRepository.findAll();
    }
    public List <Favourites> findAll() {
        return favouritesRepository.findAll();
    }

    public Favourites createFavourite(Favourites favourites) {
        favourites.getPost().getId();
        favourites.getUsers().getId();
        return favouritesRepository.save(favourites);
    }
}
