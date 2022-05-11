package org.example.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * The type Application.
 */
@SpringBootApplication
@EntityScan(basePackages = "org.example.jwt.model")
public class Application {

    /**
     * The entry point of application.
     *
     * @param args
     *         the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
