package org.example.socialnetwork.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.socialnetwork.dtos.*;
import org.example.socialnetwork.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApplicationUserResponse> registerUser(@Valid @RequestBody ApplicationUserRequest applicationUserRequest) {
        var user = userService.registerUser(applicationUserRequest);
        return ResponseEntity.created(URI.create("/api/v1/auth/find/" + user.id())).body(user);
    }

    @PutMapping("/update/phone")
    public ResponseEntity<ApplicationUserResponse> updatePhoneNumber(@Valid @RequestBody UpdatePhoneNumberRequest phoneNumberRequest) {
        return ResponseEntity.ok(userService.updatePhoneNumber(phoneNumberRequest));
    }

    @PostMapping("/email/code")
    public ResponseEntity<String> sendEmailVerificationCode(@Valid @RequestBody EmailRequest emailRequest) {
        userService.sendEmailVerificationCode(emailRequest);
        return ResponseEntity.ok("Email verification code sent");
    }

    @PostMapping("/email/verify")
    public ResponseEntity<String> verifyEmail(@RequestBody EmailVerifyRequest emailVerifyRequest) {
        userService.verifyEmail(emailVerifyRequest);
        return ResponseEntity.ok("Email verification code received");
    }
}
