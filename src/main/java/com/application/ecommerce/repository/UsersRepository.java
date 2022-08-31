package com.application.ecommerce.repository;

import com.application.ecommerce.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;

@Repository
@Table
public interface UsersRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByUsername (String message);

}
