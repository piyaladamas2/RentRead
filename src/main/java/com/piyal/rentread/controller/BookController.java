package com.piyal.rentread.controller;

import org.springframework.web.bind.annotation.RestController;

import com.piyal.rentread.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import com.piyal.rentread.dto.Bookdto;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/admin/books")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Bookdto> createBook(@RequestBody Bookdto bookDto) {
        Bookdto createdBook = bookService.addBook(bookDto);
        return ResponseEntity.ok(createdBook);
    }

    @PutMapping("/admin/books/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Bookdto> updateBook(@PathVariable Long id, @RequestBody Bookdto bookDto) {
        Bookdto updatedBook = bookService.updateBook(id, bookDto);
        return ResponseEntity.ok(updatedBook);
    }

    @GetMapping("/user/books")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Bookdto>> getAllBooks() {
        List<Bookdto> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/admin/books/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
