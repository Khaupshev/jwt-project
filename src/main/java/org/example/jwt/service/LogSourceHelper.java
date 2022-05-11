package org.example.jwt.service;

import lombok.RequiredArgsConstructor;
import org.example.jwt.exception.ErrorType;
import org.example.jwt.util.LogMessage;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * The type Message source helper.
 */
@Service
@RequiredArgsConstructor
public class LogSourceHelper {

    private final MessageSource messageSource;

    /**
     * Gets message.
     *
     * @param errorType
     *         the error type
     * @param placeholders
     *         the placeholders
     * @return the message
     */
    public String getMessage(ErrorType errorType, Object... placeholders) {
        return getMessage(Locale.getDefault(), errorType, placeholders);
    }

    /**
     * Gets message.
     *
     * @param message
     *         the message
     * @param placeholders
     *         the placeholders
     * @return the message
     */
    public String getMessage(LogMessage message, Object... placeholders) {
        return messageSource.getMessage(
                message.name().toLowerCase(),
                placeholders,
                message.getMessage(),
                Locale.getDefault()
        );
    }

    /**
     * Gets message.
     *
     * @param locale
     *         the locale
     * @param errorType
     *         the error type
     * @param placeholders
     *         the placeholders
     * @return the message
     */
    public String getMessage(Locale locale, ErrorType errorType, Object... placeholders) {
        return messageSource.getMessage(
                errorType.name().toLowerCase(),
                placeholders,
                errorType.getMessage(),
                locale
        );
    }

}
