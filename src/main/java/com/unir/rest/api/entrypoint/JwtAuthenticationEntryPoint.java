package com.unir.rest.api.entrypoint;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.io.IOException;


import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.AuthenticationEntryPoint;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;



@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}