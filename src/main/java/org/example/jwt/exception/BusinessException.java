package org.example.jwt.exception;

import lombok.Getter;

/**
 * The type Business exception.
 */
@Getter
public class BusinessException extends RuntimeException{

    private final ErrorType errorType;

    /**
     * Instantiates a new Business exception.
     *
     * @param errorType
     *         the error type
     */
    public BusinessException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    /**
     * Instantiates a new Business exception.
     *
     * @param errorType
     *         the error type
     * @param message
     *         the message
     */
    public BusinessException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

}
