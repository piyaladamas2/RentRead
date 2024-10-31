package com.piyal.rentread.service.IMPL;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.piyal.rentread.dto.BookUserDto;
import com.piyal.rentread.model.Role;
import com.piyal.rentread.model.BookUser;
import com.piyal.rentread.repository.BookUserRepository;
import com.piyal.rentread.service.BookUserService;

@Service
public class BookUserServiceImpl implements UserDetailsService, BookUserService {

    @Autowired
    BookUserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public BookUserDto registerUser(BookUserDto userDto) {

        if (userRepository.findByUserName(userDto.getUserName()).isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }

        BookUser user = toUserEntity(userDto);
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRole(userDto.getRole() != null ? userDto.getRole() : Role.USER);
        user = userRepository.save(user);

        return toUserDto(user);
    }

    @Override
    public BookUserDto login(String email, String password) {
        BookUser user = userRepository.findByUserName(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }
        return toUserDto(user);
    }

    private BookUserDto toUserDto(BookUser user) {
        BookUserDto userdto = new BookUserDto();
        userdto.setId(user.getId());
        userdto.setEmail(user.getEmail());
        userdto.setFirstName(user.getFirstName());
        userdto.setLastName(user.getLastName());
        userdto.setRole(user.getRole());
        userdto.setPassword(user.getPassword());
        return userdto;
    }

    private BookUser toUserEntity(BookUserDto userDto) {
        BookUser user = new BookUser();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRole(userDto.getRole());
        user.setPassword(userDto.getPassword());
        return user;

    }

    @Override
    public BookUserDto getUserById(Long id) {
        BookUser user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return toUserDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<BookUser> user = userRepository.findByUserName(username);

        if (user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    private String getRoles(BookUser user) {
        if (user.getRole() == null) {
            return "USER";
        } else if (user.getRole() == Role.ADMIN) {
            return "ADMIN";
        }
        return "USER";
    }

}
