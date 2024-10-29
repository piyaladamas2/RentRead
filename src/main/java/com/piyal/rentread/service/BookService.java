package com.piyal.rentread.service;

import java.util.List;
import com.piyal.rentread.model.Book;

public interface BookService {

    public List<Book> getAllAvailableBooks();

    public Book saveBook(Book book);
}
