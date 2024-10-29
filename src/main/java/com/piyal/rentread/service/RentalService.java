package com.piyal.rentread.service;

public interface RentalService {

    public String rentBook(Long bookId, String userEmail);

    public String returnBook(Long bookId, String userEmail);
}
