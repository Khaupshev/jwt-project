package org.example.jwt.controller.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The type Error message.
 */
@Data
@AllArgsConstructor
public class ErrorMessage {

    private String code;

    private String message;

}
