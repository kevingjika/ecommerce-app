package com.application.ecommerce.repository;

import com.application.ecommerce.entities.Favourites;
import com.application.ecommerce.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import org.springframework.data.relational.core.mapping.Table;

public interface FavouritesRepository extends JpaRepository<Favourites,Long> {
    Optional<Users> findById (String message);

    @Query("SELECT p FROM Favourites p WHERE p.post LIKE CONCAT('%',:message, '%') Or p.users LIKE CONCAT ('%', :query, '%')")
    List <Favourites> searchFavourite( String message);

    @Query(value = "SELECT * FROM ecommerce.favourites WHERE post LIKE %:message% Or users LIKE %:message%", nativeQuery = true)
    List <Favourites> searchFavouriteSQL(@Param("message") String message);

}
