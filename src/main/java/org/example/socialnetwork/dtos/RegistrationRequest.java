package org.example.socialnetwork.dtos;

import java.sql.Date;

public record RegistrationRequest(
        String firstName,
        String lastName,
        String email,
        Date dateOfBirth
) {
}
