package com.piyal.rentread.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRegistrationDto {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")

    private String lastName;

    @Email(message = "Please provide a valid email")
    @NotBlank(message = "Email is required")

    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters")
    @NotBlank(message = "Password is required")

    private String password;

    private String role = "USER";
}
