package com.piyal.rentread.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.piyal.rentread.model.Book;
import com.piyal.rentread.repository.BookRepository;

public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> getAllAvailableBooks() {
        return bookRepository.findByAvailableTrue();
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

}
