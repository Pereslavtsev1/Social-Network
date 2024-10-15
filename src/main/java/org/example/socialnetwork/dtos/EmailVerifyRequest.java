package org.example.socialnetwork.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EmailVerifyRequest(
        @NotBlank
        String username,
        @Positive
        @NotNull
        Integer verificationCode
) {
}
