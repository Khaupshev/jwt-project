package org.example.jwt.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jwt.dto.UserDto;
import org.example.jwt.exception.BusinessException;
import org.example.jwt.exception.ErrorType;
import org.example.jwt.model.Status;
import org.example.jwt.service.LogSourceHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * The type Jwt token provider.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expiration-period}")
    private Long expiredMillis;

    private final LogSourceHelper logSourceHelper;

    /**
     * Create token string.
     *
     * @param user
     *         the user
     * @return the string
     */
    public String createToken(UserDto user) {

        var userName = user.getUserName();
        var role = user.getRole();
        var claims = Jwts.claims()
                .setSubject(userName);
        claims.put("role", role);
        var now = new Date();
        var validity = new Date(now.getTime() + expiredMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * Resolve token string.
     *
     * @param req
     *         the req
     * @return the string
     */
    public String resolveToken(HttpServletRequest req) {
        var bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * Validate token boolean.
     *
     * @param token
     *         the token
     * @param status
     *         the status
     * @return the boolean
     */
    public boolean validateToken(String token, Status status) {
        try {
            var claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date())
                    && claims.getBody().get("role").equals(status.toString());
        } catch (JwtException | IllegalArgumentException e) {
            log.warn(e.getMessage());
            throw new BusinessException(ErrorType.VALIDATE_TOKEN_ERROR, e.getMessage());
        }
    }

}
