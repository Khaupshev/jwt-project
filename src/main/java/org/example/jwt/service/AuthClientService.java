package org.example.jwt.service;

import lombok.RequiredArgsConstructor;
import org.example.jwt.client.AuthClient;
import org.example.jwt.dto.AuthenticationRequest;
import org.example.jwt.dto.AuthenticationResponse;
import org.example.jwt.util.FeignUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthClientService {

    private final AuthClient authClient;

    public Optional<AuthenticationResponse> getAuthenticationToken(AuthenticationRequest request) {
        return FeignUtils.getResponse(
                () -> authClient.getToken(request)
        );
    }

}
