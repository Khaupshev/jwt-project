package org.example.jwt.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jwt.dto.UserDto;
import org.example.jwt.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Admin controller.
 */
@Slf4j
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    /**
     * Users list.
     *
     * @return the list
     */
    @GetMapping("/all")
    public List<UserDto> users() {
        return userService.getAllUsers();
    }

}
