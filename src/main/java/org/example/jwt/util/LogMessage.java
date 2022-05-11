package org.example.jwt.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The enum Log message.
 */
@Getter
@RequiredArgsConstructor
public enum LogMessage {

    /**
     * The Default message.
     */
    DEFAULT_MESSAGE("Default message");

    private final String message;

}
