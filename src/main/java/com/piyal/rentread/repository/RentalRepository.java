package com.piyal.rentread.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piyal.rentread.model.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    Long countByUserAndReturnDateIsNull(Long userId);

    Optional<Rental> findByRentalIdReturnDateIsNull(Long Id);
}
