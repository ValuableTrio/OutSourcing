package com.sparta.outsourcing.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.naming.AuthenticationException;

import static com.sparta.outsourcing.config.SessionConst.SESSION_USER;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationChecker implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {
        HttpSession session = request.getSession();

        if (session == null ||
                session.getAttribute(SESSION_USER.name()) == null) {
            throw new AuthenticationException("로그인이 필요한 서비스입니다.");
        }

        return true;
    }
}
