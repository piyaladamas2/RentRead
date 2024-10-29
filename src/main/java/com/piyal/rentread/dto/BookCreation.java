package com.piyal.rentread.dto;

import jakarta.validation.constraints.NotBlank;

public class BookCreation {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    private String genre;

    private boolean available;
}
