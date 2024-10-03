package org.example.socialnetwork.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.dtos.ApplicationUserResponse;
import org.example.socialnetwork.dtos.RegistrationRequest;
import org.example.socialnetwork.dtos.UpdatePhoneRequest;
import org.example.socialnetwork.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<ApplicationUserResponse> register(@RequestBody @Valid RegistrationRequest request) {
        return ResponseEntity.created(URI.create("")).body(userService.registerUser(request));
    }

    @PutMapping("/update/phone")
    public ResponseEntity<ApplicationUserResponse> updatePhone(@RequestBody @Valid UpdatePhoneRequest request) {
        return ResponseEntity.ok(userService.updatePhoneNumber(request));
    }
}
