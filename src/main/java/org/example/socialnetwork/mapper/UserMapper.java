package org.example.socialnetwork.mapper;

import jakarta.validation.Valid;
import org.example.socialnetwork.dtos.ApplicationUserRequest;
import org.example.socialnetwork.dtos.ApplicationUserResponse;
import org.example.socialnetwork.model.ApplicationUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public ApplicationUser toApplicationUser(@Valid ApplicationUserRequest applicationUserRequest) {
        var applicationUser = new ApplicationUser();
        applicationUser.setEmail(applicationUserRequest.email());
        applicationUser.setFirstName(applicationUserRequest.firstname());
        applicationUser.setLastName(applicationUserRequest.lastname());
        applicationUser.setDateOfBirth(applicationUserRequest.dateOfBirth());
        return applicationUser;
    }

    public ApplicationUserResponse toApplicationUserResponse(ApplicationUser applicationUser) {
        return ApplicationUserResponse.builder()
                .id(applicationUser.getId())
                .firstname(applicationUser.getFirstName())
                .lastname(applicationUser.getLastName())
                .build();
    }
}
