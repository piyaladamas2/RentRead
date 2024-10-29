package com.piyal.rentread.dto;

import java.time.LocalDateTime;

public class Rental {

    private Long rentalId;
    private Long userId;
    private Long bookId;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
}
