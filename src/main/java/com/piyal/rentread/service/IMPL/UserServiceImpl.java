package com.piyal.rentread.service.IMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.piyal.rentread.dto.UserDto;
import com.piyal.rentread.model.Role;
import com.piyal.rentread.model.User;
import com.piyal.rentread.repository.UserRepository;
import com.piyal.rentread.service.UserService;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDto registerUser(UserDto userDto) {

        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }

        User user = toUserEntity(userDto);
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRole(userDto.getRole() != null ? userDto.getRole() : Role.USER);
        user = userRepository.save(user);

        return toUserDto(user);
    }

    @Override
    public UserDto login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }
        return toUserDto(user);
    }

    private UserDto toUserDto(User user) {
        UserDto userdto = new UserDto();
        userdto.setId(user.getId());
        userdto.setEmail(user.getEmail());
        userdto.setFirstName(user.getFirstName());
        userdto.setLastName(user.getLastName());
        userdto.setRole(user.getRole());
        userdto.setPassword(user.getPassword());
        return userdto;
    }

    private User toUserEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRole(userDto.getRole());
        user.setPassword(userDto.getPassword());
        return user;

    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return toUserDto(user);
    }

}
