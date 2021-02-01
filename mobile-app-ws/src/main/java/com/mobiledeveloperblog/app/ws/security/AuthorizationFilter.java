package com.mobiledeveloperblog.app.ws.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {
	
	AuthorizationFilter(AuthenticationManager authenticationManager){
		super(authenticationManager);
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String header = request.getHeader(SecurityConstants.HEADER_STRING);
		
		if(header == null || !header.startsWith(SecurityConstants.TOCKEN_PRIFIX)){
			
			chain.doFilter(request, response);
			return;
		}
		
		UsernamePasswordAuthenticationToken authentication = getAuthontication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}


	private UsernamePasswordAuthenticationToken getAuthontication(HttpServletRequest request) {
		String tocken = request.getHeader(SecurityConstants.HEADER_STRING);
		if(tocken != null) {
			
			tocken = tocken.replace(SecurityConstants.TOCKEN_PRIFIX, "");
			
			String user = Jwts.parser()
					     .setSigningKey(SecurityConstants.TOCKEN_SECRET)
					     .parseClaimsJws(tocken)
					     .getBody()
					     .getSubject();
			
			if(user !=null) {
				return new UsernamePasswordAuthenticationToken(user , null ,  new ArrayList<>());
			}
			
		}
		return null;
	}
}
