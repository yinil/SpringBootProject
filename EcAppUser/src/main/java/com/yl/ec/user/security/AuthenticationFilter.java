package com.yl.ec.user.security;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yl.ec.user.dto.UserDto;
import com.yl.ec.user.model.LoginRequestModel;
import com.yl.ec.user.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private UserService userService;
	private Environment environment;
	
	public AuthenticationFilter(UserService userService, Environment environment, AuthenticationManager manager) {
		this.userService = userService;
		this.environment = environment;
		super.setAuthenticationManager(manager);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			LoginRequestModel creds = new ObjectMapper()
					.readValue(request.getInputStream(), LoginRequestModel.class);
			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.getEmail(), 
							creds.getPassword(),
							new ArrayList<>()));
		} catch (IOException e) {
			// TODO throw custom error?
			throw new RuntimeException();
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
//		System.out.println(environment.getProperty("token.expiration"));
		// generate jwt token
		User user = (User) authResult.getPrincipal();
		String username = user.getUsername();
		UserDto userDetails = userService.getUserDetailsByEmail(username);
		String token = Jwts.builder()
				.setSubject(userDetails.getUserId())
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration"))))
				.signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
				.compact();
		response.addHeader("token", environment.getProperty("authorization.token.header.prefix") + token);
		response.addHeader("userId", userDetails.getUserId());
		response.setHeader("Access-Control-Expose-Headers", "token, userId");
	}
	
	
	
}
