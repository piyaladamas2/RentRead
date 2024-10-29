package com.piyal.rentread.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piyal.rentread.dto.Book;
import com.piyal.rentread.service.BookService;
import com.piyal.rentread.service.RentalService;

@RestController
@RequestMapping("/rent-read")
public class LibraryController {

    private final BookService bookService;
    private final RentalService rentalService;

    public LibraryController(BookService bookService, RentalService rentalService) {
        this.bookService = bookService;
        this.rentalService = rentalService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllAvailableBooks();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/books")
    public Book addBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{bookId}/rent")
    public ResponseEntity<String> rentBook(@PathVariable Long bookId, Authentication authentication) {
        String userEmail = authentication.getName();
        String result = rentalService.rentBook(bookId, userEmail);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{bookId}/return")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId, Authentication authentication) {
        String userEmail = authentication.getName();
        String result = rentalService.returnBook(bookId, userEmail);
        return ResponseEntity.ok(result);
    }
}
