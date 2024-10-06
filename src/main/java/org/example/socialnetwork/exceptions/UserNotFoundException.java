package org.example.socialnetwork.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super("Sorry, the user was not found", HttpStatus.NOT_FOUND);
    }
}
