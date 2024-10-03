package org.example.socialnetwork.exceptions;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistException extends BaseException{
    public EmailAlreadyExistException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
