package org.example.test.controller;

import lombok.RequiredArgsConstructor;
import org.example.test.dto.AuthenticationRequest;
import org.example.test.dto.UserDto;
import org.example.test.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping(path = "/sign-up",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        return userService.signUp(userDto);
    }

    @PostMapping(path = "/sign-in",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return userService.signIn(authenticationRequest);
    }

}
