package com.piyal.rentread.service;

import java.util.List;

import com.piyal.rentread.dto.Bookdto;

public interface BookService {

    List<Bookdto> getAllBooks();

    Bookdto addBook(Bookdto book);

    Bookdto getBookById(Long id);

    void deleteBook(Long id);

    Bookdto updateBook(Long id, Bookdto book);
}
