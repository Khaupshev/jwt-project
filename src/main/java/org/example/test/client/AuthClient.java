package org.example.test.client;

import org.example.test.dto.AuthenticationRequest;
import org.example.test.dto.AuthenticationResponse;
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

    @ResponseBody
    @PostMapping(path = "/api/v1/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    AuthenticationResponse getToken(@RequestBody AuthenticationRequest request);

}
