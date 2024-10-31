package com.piyal.rentread.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.piyal.rentread.dto.Rentaldto;
import com.piyal.rentread.service.RentalService;

@RestController
@RequestMapping("/api/user")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping("/books/{bookId}/rent")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Rentaldto> rentBook(@PathVariable Long bookId,
            @RequestParam Long userId) {
        Rentaldto rentalDto = rentalService.rentBook(userId, bookId);
        return ResponseEntity.ok(rentalDto);
    }

    @PostMapping("/books/{bookId}/return")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Rentaldto> returnBook(@PathVariable Long bookId, @RequestParam Long userId) {
        Rentaldto rentalDto = rentalService.returnBook(bookId);
        return ResponseEntity.ok(rentalDto);

    }
}
