package com.application.ecommerce.services;

import com.application.ecommerce.entities.Favourites;

import java.util.List;

public interface FavouritesServiceImpl {

    List<Favourites> searchFavourite(String message);

}
