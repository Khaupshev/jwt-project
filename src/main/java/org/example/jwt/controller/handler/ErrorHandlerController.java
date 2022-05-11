package org.example.jwt.controller.handler;

import org.example.jwt.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlerController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException.class)
    public ErrorMessage handle(BusinessException e) {
        var errorType = e.getErrorType();
        var msg = e.getMessage();
        return new ErrorMessage(errorType.getCode(), msg);
    }

}
