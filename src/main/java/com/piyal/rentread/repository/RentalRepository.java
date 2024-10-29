package com.piyal.rentread.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piyal.rentread.model.Rental;
import com.piyal.rentread.model.User;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    Long countByUserAndReturnDateIsNull(User user);

    Optional<Rental> findByUserAndBookAndReturnDateIsNull(User user, Long bookId);
}
