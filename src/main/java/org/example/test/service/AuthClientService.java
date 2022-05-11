package org.example.test.service;

import lombok.RequiredArgsConstructor;
import org.example.test.client.AuthClient;
import org.example.test.dto.AuthenticationRequest;
import org.example.test.dto.AuthenticationResponse;
import org.example.test.util.FeignUtils;
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
