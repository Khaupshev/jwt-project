package org.example.jwt.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jwt.dto.HistoryRequest;
import org.example.jwt.dto.MessageDto;
import org.example.jwt.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type User controller.
 */
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Gets history.
     *
     * @param historyRequest
     *         the history request
     * @return the history
     */
    @GetMapping(path = "/history",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDto> getHistory(@RequestBody HistoryRequest historyRequest) {
        return userService.getHistory(historyRequest);
    }

    /**
     * Send message response entity.
     *
     * @param messageDto
     *         the message dto
     * @return the response entity
     */
    @PostMapping(path = "/message",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendMessage(@RequestBody MessageDto messageDto) {
        return userService.sendMessage(messageDto);
    }

}
