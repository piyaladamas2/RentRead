package com.piyal.rentread.service;

import com.piyal.rentread.dto.BookUserDto;

public interface BookUserService {

    BookUserDto registerUser(BookUserDto userDto);

    BookUserDto login(String email, String password);

    BookUserDto getUserById(Long id);
}
