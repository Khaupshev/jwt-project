package org.example.jwt.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jwt.dto.AuthenticationRequest;
import org.example.jwt.dto.AuthenticationResponse;
import org.example.jwt.dto.HistoryRequest;
import org.example.jwt.dto.MessageDto;
import org.example.jwt.dto.UserDto;
import org.example.jwt.exception.BusinessException;
import org.example.jwt.exception.ErrorType;
import org.example.jwt.mapper.MessageMapper;
import org.example.jwt.mapper.UserMapper;
import org.example.jwt.model.Message;
import org.example.jwt.model.User;
import org.example.jwt.repository.UserRepository;
import org.example.jwt.security.JwtTokenProvider;
import org.example.jwt.service.AuthClientService;
import org.example.jwt.service.LogSourceHelper;
import org.example.jwt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type User service.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserMapper userMapper;

    private final AuthClientService authClientService;

    private final LogSourceHelper logSourceHelper;

    private final BCryptPasswordEncoder passwordEncoder;

    private final MessageMapper messageMapper;

    @Override
    @Transactional
    public ResponseEntity<?> signIn(AuthenticationRequest request) {
        return userRepository.findByUserName(request.getUserName())
                .filter(user -> isValidPassword(request, user))
                .map(this::getLoginResponse)
                .orElseThrow(() -> {
                    throw userNotFoundException(request.getUserName());
                });
    }

    @Override
    public ResponseEntity<?> signUp(UserDto userDto) {
        var saved = createUser(userDto);
        return getToken(userDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> {
                    //если криво отработала транзакция создания юзера
                    saved.setRole(null);
                    userRepository.save(saved);
                    throw userNotFoundException(saved.getUserName());
                });
    }

    @Override
    @Transactional
    public List<UserDto> getAllUsers() {
        return userMapper.map(userRepository.findAll());
    }

    @Override
    public ResponseEntity<MessageDto> getHistory(HistoryRequest historyRequest) {
        var userName = historyRequest.getUserName();
        return userRepository.findByUserName(userName)
                .map(user -> ResponseEntity.ok(
                        messageMapper.map(
                                user.getMessages()
                                        .stream()
                                        .sorted(Comparator.comparing(Message::getCreatedAt))
                                        .limit(historyRequest.getCountMessages())
                                        .collect(Collectors.toList()),
                                userName))
                )
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @Transactional
    public ResponseEntity<?> sendMessage(MessageDto messageDto) {
        return userRepository.findByUserName(messageDto.getUserName())
                .map(user -> {
                    var messages = messageDto.getMessages();
                    user.setMessages(messageMapper.map(messages));
                    userRepository.save(user);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create user user.
     *
     * @param userDto
     *         the user dto
     * @return the user
     */
    @Transactional
    User createUser(UserDto userDto) {
        if (!isAlreadyExistsUserNameOrEmail(userDto)) {
            var user = userMapper.map(userDto);
            var saved = userRepository.saveAndFlush(user);
            log.info("User was saved {}", saved);
            return saved;
        } else {
            var errorType = ErrorType.USERNAME_OR_EMAIL_ALREADY_EXISTS;
            var msg = logSourceHelper.getMessage(errorType, userDto.getUserName(), userDto.getEmail());
            log.warn(msg);
            throw new BusinessException(errorType, msg);
        }
    }

    private boolean isValidPassword(AuthenticationRequest request, User user) {
        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }

    private ResponseEntity<AuthenticationResponse> getLoginResponse(User user) {
        var userDto = userMapper.map(user);
        var token = jwtTokenProvider.createToken(userDto);
        var response = AuthenticationResponse
                .builder()
                .token(token)
                .build();
        log.info("User with username {} is logged in", user.getUserName());
        return ResponseEntity.ok(response);
    }

    private Optional<AuthenticationResponse> getToken(UserDto userDto) {
        var request = userMapper.toRequest(userDto);
        return authClientService.getAuthenticationToken(request);
    }

    private boolean isAlreadyExistsUserNameOrEmail(UserDto userDto) {
        return userRepository.countAllByUserNameOrEmail(userDto.getUserName(), userDto.getEmail()) > 0;
    }

    private BusinessException userNotFoundException(String userName) {
        var errorType = ErrorType.INVALID_USERNAME_OR_PASSWORD;
        var msg = logSourceHelper.getMessage(errorType, userName);
        log.warn(msg);
        return new BusinessException(errorType, msg);
    }

}
