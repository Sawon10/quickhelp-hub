package com.oauth.quickhelp.repository;

import com.oauth.quickhelp.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    // This interface will automatically provide CRUD operations for UserDetails entity
    // Additional custom query methods can be defined here if needed
    Optional<UserDetails> findByEmail(String email);
}
