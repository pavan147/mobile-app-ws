package com.mobiledeveloperblog.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mobiledeveloperblog.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	
  	UserDto createUser(UserDto userDto);
  	
  	UserDto getUser(String name); 

}
