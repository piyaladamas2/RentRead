package com.piyal.rentread.dto;

import jakarta.validation.constraints.NotNull;

public class RentalRequestDto {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Book ID is required")
    private Long bookId;
}
