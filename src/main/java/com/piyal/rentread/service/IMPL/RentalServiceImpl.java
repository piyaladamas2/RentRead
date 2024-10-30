package com.piyal.rentread.service.IMPL;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piyal.rentread.dto.Rentaldto;
import com.piyal.rentread.model.Book;
import com.piyal.rentread.model.Rental;
import com.piyal.rentread.model.User;
import com.piyal.rentread.repository.BookRepository;
import com.piyal.rentread.repository.RentalRepository;
import com.piyal.rentread.repository.UserRepository;
import com.piyal.rentread.service.RentalService;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Rentaldto rentBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        if (!book.isAvailable()) {
            throw new RuntimeException("Book is not available for rent");
        }

        long activeRentals = rentalRepository.countByUserAndReturnDateIsNull(user.getId());
        if (activeRentals >= 2) {
            throw new RuntimeException("User already has two active rentals");
        }

        Rental rental = new Rental();
        rental.setUser(user);
        rental.setBook(book);
        rental.setRentalDate(LocalDate.now());
        rental.setReturnDate(null);

        // Mark book as not available
        book.setAvailable(false);
        bookRepository.save(book);

        Rental savedRental = rentalRepository.save(rental);
        return toRentalDto(savedRental);
    }

    @Override
    public Rentaldto returnBook(Long id) {
        Rental rental = rentalRepository.findByBookIdReturnDateIsNull(id)
                .orElseThrow(() -> new RuntimeException("Rental not found or already returned"));

        rental.setReturnDate(LocalDate.now());

// Mark book as available again
        Book book = rental.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        Rental returnedRental = rentalRepository.save(rental);
        return toRentalDto(returnedRental);
    }

    private Rentaldto toRentalDto(Rental rental) {
        Rentaldto rentalDto = new Rentaldto();
        rentalDto.setId(rental.getId());
        rentalDto.setUserId(rental.getUser().getId());
        rentalDto.setBookId(rental.getBook().getId());
        rentalDto.setRentalDate(rental.getRentalDate());
        rentalDto.setReturnDate(rental.getReturnDate());
        return rentalDto;
    }

}
