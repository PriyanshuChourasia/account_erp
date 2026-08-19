package com.codymitra.auth_service.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("entry point request {}",request);
        log.info("entry point response {}",response);
        log.info("entry point auth {}",authException.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Map<String,Object> body = new HashMap<>();
        Map<String,Object> result = new HashMap<>();
        result.put("result",authException.getMessage());
        body.put("status",HttpStatus.UNAUTHORIZED);
        body.put("code", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("message",authException.getMessage());
        body.put("data",result);

        objectMapper.writeValue(response.getOutputStream(),body);
    }
}
