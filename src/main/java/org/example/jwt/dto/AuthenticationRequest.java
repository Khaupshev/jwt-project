package org.example.jwt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.jwt.model.Status;

/**
 * The type Authentication request.
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequest {

    private String userName;

    private String password;

}
