package com.piyal.rentread.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piyal.rentread.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
