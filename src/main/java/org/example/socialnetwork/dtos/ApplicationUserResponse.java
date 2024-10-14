package org.example.socialnetwork.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationUserResponse {
    Long id;
    String firstname;
    String lastname;
}
