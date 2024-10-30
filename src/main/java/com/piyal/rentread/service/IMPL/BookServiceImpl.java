package com.piyal.rentread.service.IMPL;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.piyal.rentread.dto.Bookdto;
import com.piyal.rentread.model.Book;
import com.piyal.rentread.repository.BookRepository;
import com.piyal.rentread.service.BookService;

public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Bookdto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::toBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public Bookdto addBook(Bookdto book) {
        Book bookModel = toBookEntity(book);
        bookModel.setAvailable(true); // Set availability by default
        Book savedBook = bookRepository.save(bookModel);
        return toBookDto(savedBook);
    }

    @Override
    public Bookdto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return toBookDto(book);

    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(id);
    }

    @Override
    public Bookdto updateBook(Long id, Bookdto bookdto) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(bookdto.getTitle());
        book.setAuthor(bookdto.getAuthor());
        book.setGenre(bookdto.getGenre());
        book.setAvailable(bookdto.isIsAvailable());
        Book updatedBook = bookRepository.save(book);
        return toBookDto(updatedBook);
    }

    private Bookdto toBookDto(Book book) {
        Bookdto bookdto = new Bookdto();
        bookdto.setId(book.getId());
        bookdto.setAuthor(book.getAuthor());
        bookdto.setGenre(book.getGenre());
        bookdto.setTitle(book.getTitle());
        bookdto.setIsAvailable(book.isAvailable());
        return bookdto;
    }

    private Book toBookEntity(Bookdto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setAvailable(bookDto.isIsAvailable());
        return book;

    }

}
