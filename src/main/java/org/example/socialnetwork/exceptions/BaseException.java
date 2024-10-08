package org.example.socialnetwork.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public abstract class BaseException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;

}
