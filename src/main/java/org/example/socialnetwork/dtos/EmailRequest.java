package org.example.socialnetwork.dtos;

import jakarta.validation.constraints.NotBlank;

public record EmailRequest(
        @NotBlank
        String username

) {
}
