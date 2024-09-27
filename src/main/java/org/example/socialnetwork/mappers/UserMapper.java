package org.example.socialnetwork.mappers;

import org.example.socialnetwork.dtos.RegistrationRequest;
import org.example.socialnetwork.models.ApplicationUser;
import org.springframework.stereotype.Component;

import java.util.HashSet;


@Component
public class UserMapper {
    public ApplicationUser toApplicationUser(RegistrationRequest registrationRequest) {
        return ApplicationUser.builder()
                .firstName(registrationRequest.firstName())
                .lastName(registrationRequest.lastName())
                .dateOfBirth(registrationRequest.dateOfBirth())
                .email(registrationRequest.email())
                .authorities(new HashSet<>())
                .build();
    }
}
