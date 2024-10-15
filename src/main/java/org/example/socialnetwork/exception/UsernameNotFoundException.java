package org.example.socialnetwork.exception;

import org.springframework.http.HttpStatus;

public class UsernameNotFoundException extends BaseException {
    protected UsernameNotFoundException(String username) {
        super(String.format("User with provided username: %s not found", username), HttpStatus.BAD_REQUEST);
    }
}
