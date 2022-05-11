package org.example.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test.dto.HistoryRequest;
import org.example.test.dto.MessageDto;
import org.example.test.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/history",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDto> getHistory(@RequestBody HistoryRequest historyRequest) {
        return userService.getHistory(historyRequest);
    }

    @PostMapping(path = "/message",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendMessage(@RequestBody MessageDto messageDto) {
        return userService.sendMessage(messageDto);
    }

}
