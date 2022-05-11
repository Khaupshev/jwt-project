package org.example.jwt.client;

import org.example.jwt.dto.AuthenticationRequest;
import org.example.jwt.dto.AuthenticationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * В идеале это отдельный сервис, для генерации токенов. В данном кейсе обращаемся на собственный эндпоинт.
 */
@FeignClient(name = "auth", url = "${client.auth.url}", configuration = FeignClientsConfiguration.class)
public interface AuthClient {

    /**
     * Gets token.
     *
     * @param request
     *         the request
     * @return the token
     */
    @ResponseBody
    @PostMapping(path = "/api/v1/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    AuthenticationResponse getToken(@RequestBody AuthenticationRequest request);

}
