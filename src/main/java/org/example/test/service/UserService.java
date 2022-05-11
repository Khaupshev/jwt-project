package org.example.test.service;

import org.example.test.dto.AuthenticationRequest;
import org.example.test.dto.HistoryRequest;
import org.example.test.dto.MessageDto;
import org.example.test.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<?> signIn(AuthenticationRequest authenticationRequest);

    ResponseEntity<?> signUp(UserDto userDto);

    List<UserDto> getAllUsers();

    ResponseEntity<MessageDto> getHistory(HistoryRequest historyRequest);

    ResponseEntity<?> sendMessage(MessageDto messageDto);
}
