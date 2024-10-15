package org.example.socialnetwork.dtos;

import jakarta.validation.constraints.NotBlank;

public record UpdatePhoneNumberRequest(
        @NotBlank
        String username,
        @NotBlank
        String phone
) {
}
