package com.piyal.rentread.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.piyal.rentread.dto.BookUserDto;
import com.piyal.rentread.service.BookUserService;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    BookUserService userService;

    @PostMapping("/register")
    public ResponseEntity<BookUserDto> registerUser(@RequestBody BookUserDto userDto) {
        BookUserDto createdUser = userService.registerUser(userDto);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<BookUserDto> login(@RequestParam String email, @RequestParam String password) {
        BookUserDto user = userService.login(email, password);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(401).build();
    }

}
