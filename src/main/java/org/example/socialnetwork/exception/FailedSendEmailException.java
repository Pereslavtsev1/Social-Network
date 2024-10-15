package org.example.socialnetwork.exception;

import org.springframework.http.HttpStatus;

public class FailedSendEmailException extends BaseException {
    public FailedSendEmailException() {
        super("Failed send email", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}