package com.mobiledeveloperblog.app.ws.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiledeveloperblog.app.ws.SpringApplicationContext;
import com.mobiledeveloperblog.app.ws.service.UserService;
import com.mobiledeveloperblog.app.ws.shared.dto.UserDto;
import com.mobiledeveloperblog.app.ws.ui.model.request.UserLoginRequestModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	public final AuthenticationManager authenticationManager;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
			HttpServletResponse res) throws AuthenticationException{
		
		try {
			
			UserLoginRequestModel creds = new ObjectMapper().readValue(req.getInputStream(), UserLoginRequestModel.class);
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
			
		}catch(Exception exception) {
			
			throw new RuntimeException(exception);
			
		}
				
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest re,
			HttpServletResponse res, FilterChain chain, Authentication auth)
			throws IOException, ServletException {
		
               String userName=((User) auth.getPrincipal()).getUsername();
               
               String tocken =Jwts.builder().setSubject(userName)
            		   .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                       .signWith(SignatureAlgorithm.HS512, SecurityConstants.TOCKEN_SECRET)
                       .compact();
               
               UserService userService = (UserService)SpringApplicationContext.getBean("userServiceImpl");
              UserDto userDto =  userService.getUser(userName);
               
               res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOCKEN_PRIFIX + tocken);
               res.addHeader("userID", userDto.getUserId());
		
	}
	
}
