package org.example.socialnetwork.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.sql.Date;

public record ApplicationUserRequest(
        @NotBlank
        String firstname,
        @NotBlank
        String lastname,
        @Email
        String email,
        Date dateOfBirth
) {

}
