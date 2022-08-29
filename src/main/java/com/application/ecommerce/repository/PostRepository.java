package com.application.ecommerce.repository;

import com.application.ecommerce.entities.Post;
import com.application.ecommerce.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.relational.core.mapping.Table;

@Repository
@Table
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Users> findById(String message);

    @Query("SELECT p FROM Post p WHERE p.post LIKE CONCAT('%',:message, '%') Or p.status LIKE CONCAT ('%', :query, '%')")
    List <Post> searchPost ( String message);
    @Query(value = "SELECT * FROM ecommerce.post WHERE status LIKE %:message% Or type LIKE %:message%", nativeQuery = true)
    List<Post> searchSpidSQL(@Param("message") String message);

}
