package org.example.socialnetwork.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.dtos.ApplicationUserRequest;
import org.example.socialnetwork.dtos.ApplicationUserResponse;
import org.example.socialnetwork.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApplicationUserResponse> registerUser(@Valid @RequestBody ApplicationUserRequest applicationUserRequest) {
        var user = userService.registerUser(applicationUserRequest);
        return ResponseEntity.created(URI.create("/api/v1/auth/find/" + user.getId())).body(user);
    }
}
