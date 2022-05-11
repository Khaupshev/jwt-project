package org.example.jwt.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LogMessage {

    DEFAULT_MESSAGE("Default message");

    private final String message;

}
