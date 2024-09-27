package org.example.socialnetwork.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.dtos.ApplicationUserResponse;
import org.example.socialnetwork.dtos.RegistrationRequest;
import org.example.socialnetwork.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<ApplicationUserResponse> register(@RequestBody @Valid RegistrationRequest request) {
        return ResponseEntity.created(URI.create("")).body(userService.registerUser(request));
    }
}
