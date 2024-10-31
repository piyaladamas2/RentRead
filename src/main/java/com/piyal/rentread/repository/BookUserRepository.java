package com.piyal.rentread.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piyal.rentread.model.BookUser;

@Repository
public interface BookUserRepository extends JpaRepository<BookUser, Long> {

    Optional<BookUser> findByUserName(String userName);

}
