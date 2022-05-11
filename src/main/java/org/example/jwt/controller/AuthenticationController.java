package org.example.jwt.controller;

import lombok.RequiredArgsConstructor;
import org.example.jwt.dto.AuthenticationRequest;
import org.example.jwt.dto.UserDto;
import org.example.jwt.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Authentication controller.
 */
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    /**
     * Register response entity.
     *
     * @param userDto
     *         the user dto
     * @return the response entity
     */
    @PostMapping(path = "/sign-up",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        return userService.signUp(userDto);
    }

    /**
     * Login response entity.
     *
     * @param authenticationRequest
     *         the authentication request
     * @return the response entity
     */
    @PostMapping(path = "/sign-in",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return userService.signIn(authenticationRequest);
    }

}
