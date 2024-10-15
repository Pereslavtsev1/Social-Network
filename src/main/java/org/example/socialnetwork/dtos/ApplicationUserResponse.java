package org.example.socialnetwork.dtos;

import lombok.Builder;

@Builder
public record ApplicationUserResponse(
        Long id,
        String firstname,
        String lastname
) {
}
