package com.mobiledeveloperblog.app.ws.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobiledeveloperblog.app.ws.service.UserService;
import com.mobiledeveloperblog.app.ws.shared.dto.UserDto;
import com.mobiledeveloperblog.app.ws.ui.model.request.UserDetailRequestModel;
import com.mobiledeveloperblog.app.ws.ui.model.responce.UserRest;



@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping
	public String getUser() {
		return "get User was callled";
	}

	@PostMapping
	public UserRest createUser(@RequestBody UserDetailRequestModel userDetails) {
		
		UserRest returnValue = new UserRest();
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createdUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, returnValue);

		return returnValue;
	}

	@PutMapping
	public String updateUser() {

		return "UPDATE user called";
	}

	@DeleteMapping
	public String deleteUser() {

		return "DELETE user called";
	}
}
