package org.example.jwt.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jwt.exception.BusinessException;
import org.example.jwt.exception.ErrorType;
import org.example.jwt.model.Status;
import org.example.jwt.service.LogSourceHelper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenFilter {

    private final JwtTokenProvider jwtTokenProvider;

    private final LogSourceHelper logSourceHelper;

    public boolean doFilter (HttpServletRequest request, Status status) {
        var token = jwtTokenProvider.resolveToken(request);
        if (token != null && jwtTokenProvider.validateToken(token, status)) {
            return true;
        } else {
            var errorType = ErrorType.AUTHENTICATION_ERROR;
            var msg = logSourceHelper.getMessage(errorType, token);
            throw new BusinessException(errorType, msg);
        }
    }

}
