package org.example.test.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorType {

    USERNAME_OR_EMAIL_ALREADY_EXISTS("U001", "User with username or email already exists"),
    INVALID_USERNAME_OR_PASSWORD("U002", "Invalid username or password"),
    VALIDATE_TOKEN_ERROR("T001", "Validate token error"),
    AUTHENTICATION_ERROR("A001", "Authentication error");

    private final String code;

    private final String message;

}
