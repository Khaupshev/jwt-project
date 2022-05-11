package org.example.jwt.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The enum Error type.
 */
@Getter
@RequiredArgsConstructor
public enum ErrorType {

    /**
     * The Username or email already exists.
     */
    USERNAME_OR_EMAIL_ALREADY_EXISTS("U001", "User with username or email already exists"),
    /**
     * The Invalid username or password.
     */
    INVALID_USERNAME_OR_PASSWORD("U002", "Invalid username or password"),
    /**
     * The Validate token error.
     */
    VALIDATE_TOKEN_ERROR("T001", "Validate token error"),
    /**
     * The Authentication error.
     */
    AUTHENTICATION_ERROR("A001", "Authentication error");

    private final String code;

    private final String message;

}
