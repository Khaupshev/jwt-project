package org.example.test.controller.interceptor;

import lombok.RequiredArgsConstructor;
import org.example.test.model.Status;
import org.example.test.security.JwtTokenFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AdminTokenInterceptor implements HandlerInterceptor {

    private final JwtTokenFilter jwtTokenFilter;

    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler) {
        return jwtTokenFilter.doFilter(request, Status.ADMIN);
    }

}