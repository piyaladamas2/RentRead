
package com.piyal.rentread.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.piyal.rentread.model.Book;
import com.piyal.rentread.model.Rental;
import com.piyal.rentread.model.User;
import com.piyal.rentread.repository.BookRepository;
import com.piyal.rentread.repository.RentalRepository;
import com.piyal.rentread.repository.UserRepository;


public class RentalServiceImpl implements RentalService{

  @Autowired
  UserRepository userRepository;

  @Autowired
  BookRepository bookRepository;

  @Autowired
  RentalRepository rentalRepository;

  @Override
  public String rentBook(Long bookId, String email) {
    User user = userRepository.findByEmail(email);
     if (user == null) {
            return "User not found.";
        }

        long activeRentals = rentalRepository.countByUserAndReturnDateIsNull(user);
        if (activeRentals >= 2) {
            return "You already have two active rentals.";
        }

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty() || !bookOptional.get().isAvailable()) {
            return "Book is not available.";
        }

        Book book = bookOptional.get();
        book.setAvailable(false);
        bookRepository.save(book);

        Rental rental = new Rental();
        rental.setUser(user);
        rental.setBook(book);
        rental.rentBook();
        rentalRepository.save(rental);

        return "Book rented successfully.";
  }

  @Override
  public String returnBook(Long bookId, String userEmail) {
    User user = userRepository.findByEmail(userEmail);

        if (user == null) {
            return "User not found.";
        }

        Optional<Rental> rentalOptional = rentalRepository.findByUserAndBookAndReturnDateIsNull(user, bookId);
        if (rentalOptional.isEmpty()) {
            return "You do not have this book rented.";
        }

        Rental rental = rentalOptional.get();
        rental.setReturnDate(LocalDate.now());
        rentalRepository.save(rental);

        Book book = rental.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        return "Book returned successfully.";
    }

}
