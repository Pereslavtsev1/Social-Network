package org.example.socialnetwork.exception;

import org.springframework.http.HttpStatus;

public class IncorrectEmailVerificationCode extends BaseException {
    public IncorrectEmailVerificationCode() {
        super("Incorrect verification code", HttpStatus.BAD_REQUEST);
    }
}
