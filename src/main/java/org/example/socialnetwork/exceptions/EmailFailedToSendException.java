package org.example.socialnetwork.exceptions;

import org.springframework.http.HttpStatus;

public class EmailFailedToSendException extends BaseException {
    public EmailFailedToSendException() {
        super("The failed to send email", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

