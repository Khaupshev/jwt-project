package org.example.jwt.service;

import org.example.jwt.dto.AuthenticationRequest;
import org.example.jwt.dto.HistoryRequest;
import org.example.jwt.dto.MessageDto;
import org.example.jwt.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Sign in response entity.
     *
     * @param authenticationRequest
     *         the authentication request
     * @return the response entity
     */
    ResponseEntity<?> signIn(AuthenticationRequest authenticationRequest);

    /**
     * Sign up response entity.
     *
     * @param userDto
     *         the user dto
     * @return the response entity
     */
    ResponseEntity<?> signUp(UserDto userDto);

    /**
     * Gets all users.
     *
     * @return the all users
     */
    List<UserDto> getAllUsers();

    /**
     * Gets history.
     *
     * @param historyRequest
     *         the history request
     * @return the history
     */
    ResponseEntity<MessageDto> getHistory(HistoryRequest historyRequest);

    /**
     * Send message response entity.
     *
     * @param messageDto
     *         the message dto
     * @return the response entity
     */
    ResponseEntity<?> sendMessage(MessageDto messageDto);
}
