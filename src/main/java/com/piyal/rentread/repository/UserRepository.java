package com.piyal.rentread.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piyal.rentread.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String userEmail);

}
