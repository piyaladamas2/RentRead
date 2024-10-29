package com.piyal.rentread.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.piyal.rentread.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
