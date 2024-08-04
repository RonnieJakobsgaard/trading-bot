package org.trading.ticker.description;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.trading.ticker.exception.DuplicateResourceException;
import org.trading.ticker.exception.ResourceNotFound;

@ControllerAdvice
public class HttpControllerAdvice {

    @ExceptionHandler(ResourceNotFound.class)
    ProblemDetail handleResourceNotFound(ResourceNotFound e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(DuplicateResourceException.class)
    ProblemDetail handleDuplicateResource(DuplicateResourceException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
