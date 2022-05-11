package org.example.jwt.dto;

import lombok.*;

/**
 * The type Authentication response.
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    private String token;

}
