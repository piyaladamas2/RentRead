package com.piyal.rentread.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piyal.rentread.model.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    Long countByUserAndReturnDateIsNull(Long userId);

    Optional<Rental> findByBookIdReturnDateIsNull(Long bookId);
}
