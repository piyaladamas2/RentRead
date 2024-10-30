package com.piyal.rentread.service;

import com.piyal.rentread.dto.UserDto;

public interface UserService {

    UserDto registerUser(UserDto userDto);

    UserDto login(String email, String password);

    UserDto getUserById(Long id);
}
