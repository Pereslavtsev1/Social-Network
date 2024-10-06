package org.example.socialnetwork.exceptions;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistException extends BaseException {
    public EmailAlreadyExistException(String email) {
        super(String.format("User with email: %s not found", email), HttpStatus.NOT_FOUND);
    }
}
