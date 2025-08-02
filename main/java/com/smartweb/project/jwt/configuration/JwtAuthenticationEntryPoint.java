package com.smartweb.project.jwt.configuration;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) 
			throws IOException, ServletException {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");// This is fired when authentication
																				// failed > from "authenticate" method
																				// from createJwtToken from JwtService
																				// class
		System.out.println("class:JwtAuthenticationEntryPoint > method:commence> msg:authentication failed.");
	}

}
