package com.piyal.rentread.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piyal.rentread.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public List<Book> findByAvailableBooks();

}
