package com.piyal.rentread.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.piyal.rentread.model.Role;
import com.piyal.rentread.model.User;
import com.piyal.rentread.repository.UserRepository;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(user.getRole() == null ? Role.USER : user.getRole());
        return userRepository.save(user);
    }

}
