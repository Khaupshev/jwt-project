package org.example.jwt.config;

import lombok.RequiredArgsConstructor;
import org.example.jwt.controller.interceptor.AdminTokenInterceptor;
import org.example.jwt.controller.interceptor.UserTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AdminTokenInterceptor adminTokenInterceptor;

    private final UserTokenInterceptor userTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminTokenInterceptor).addPathPatterns("/admin/**");
        registry.addInterceptor(userTokenInterceptor).addPathPatterns("/user/**");
    }

}
