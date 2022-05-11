package org.example.test.service;

import lombok.RequiredArgsConstructor;
import org.example.test.exception.ErrorType;
import org.example.test.util.LogMessage;
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

    public String getMessage(ErrorType errorType, Object... placeholders) {
        return getMessage(Locale.getDefault(), errorType, placeholders);
    }

    public String getMessage(LogMessage message, Object... placeholders) {
        return messageSource.getMessage(
                message.name().toLowerCase(),
                placeholders,
                message.getMessage(),
                Locale.getDefault()
        );
    }

    public String getMessage(Locale locale, ErrorType errorType, Object... placeholders) {
        return messageSource.getMessage(
                errorType.name().toLowerCase(),
                placeholders,
                errorType.getMessage(),
                locale
        );
    }

}
