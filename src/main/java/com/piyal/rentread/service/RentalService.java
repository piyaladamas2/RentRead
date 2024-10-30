package com.piyal.rentread.service;

import com.piyal.rentread.dto.Rentaldto;

public interface RentalService {

    Rentaldto rentBook(Long userId, Long bookId);

    Rentaldto returnBook(Long bookId);
}
