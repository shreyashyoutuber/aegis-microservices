package com.aegis.authservice.repository;

import com.aegis.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository - Database access for User entity
 * Spring Data JPA auto-implements all basic CRUD operations
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by username (used during login)
    Optional<User> findByUsername(String username);

    // Check if username already exists (used during registration)
    boolean existsByUsername(String username);
}
