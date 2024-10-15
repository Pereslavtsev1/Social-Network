package org.example.socialnetwork.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistException extends BaseException {
    public EmailAlreadyExistException(String email) {
        super(String.format("Provided email: %s is already exist", email), HttpStatus.BAD_REQUEST);
    }
}
