package org.example.jwt.service;

import org.example.jwt.dto.AuthenticationRequest;
import org.example.jwt.dto.HistoryRequest;
import org.example.jwt.dto.MessageDto;
import org.example.jwt.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<?> signIn(AuthenticationRequest authenticationRequest);

    ResponseEntity<?> signUp(UserDto userDto);

    List<UserDto> getAllUsers();

    ResponseEntity<MessageDto> getHistory(HistoryRequest historyRequest);

    ResponseEntity<?> sendMessage(MessageDto messageDto);
}
