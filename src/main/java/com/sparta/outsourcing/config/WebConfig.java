package com.sparta.outsourcing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationChecker())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/css/*",
                        "/*.ico",
                        "/error",
                        "/",
                        "/api/**/users/login",
                        "/api/**/users/signup",
                        "/api/**/owner/login",
                        "/api/**/owner/signup"
                );
    }
}
