package com.mobiledeveloperblog.app.ws.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mobiledeveloperblog.app.ws.service.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final UserService userDetailService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	// to initilized above varible
	WebSecurity(UserService userDetailService, BCryptPasswordEncoder bCryptPasswordEncoder) {

		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userDetailService = userDetailService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.POST, "/users").permitAll()
		.anyRequest().authenticated().and().addFilter(getAuthenticationFilter());
		//.addFilter(new AuthenticationFilter(authenticationManager())); //for default login url
	} 

	/*
	 * to tell us Spring which class we are using for security and which bcrypt algo
	 * we are using to decrypt password 
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	/* Customied Login url */
	public AuthenticationFilter getAuthenticationFilter() throws Exception {
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
	    filter.setFilterProcessesUrl("/user/login");
		return filter;
	}
}
