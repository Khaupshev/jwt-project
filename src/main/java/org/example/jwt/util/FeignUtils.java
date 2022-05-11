package org.example.jwt.util;

import feign.FeignException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FeignUtils {

    public static <T> Optional<T> getResponse(Supplier<T> feignSupplier) {
        final T response;
        try {
            response = feignSupplier.get();
        } catch (FeignException e) {
            var request = e.request();
            var responseCode = e.status();
            var msg = "Request " + request.httpMethod() + " " + request.url()
                    + " returned " + responseCode;

            if (responseCode == HttpStatus.NOT_FOUND.value()) {
                log.info(msg);
            } else {
                log.warn(msg);
            }

            return Optional.empty();
        }

        return Optional.of(response);
    }

}
