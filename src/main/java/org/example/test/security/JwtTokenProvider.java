package org.example.test.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test.dto.UserDto;
import org.example.test.exception.BusinessException;
import org.example.test.exception.ErrorType;
import org.example.test.model.Status;
import org.example.test.service.LogSourceHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expiration-period}")
    private Long expiredMillis;

    private final LogSourceHelper logSourceHelper;

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

    public String resolveToken(HttpServletRequest req) {
        var bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7);
        }
        return null;
    }

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
