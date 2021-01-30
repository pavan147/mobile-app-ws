package com.mobiledeveloperblog.app.ws.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobiledeveloperblog.app.ws.UserRepository;
import com.mobiledeveloperblog.app.ws.io.entity.UserEntity;
import com.mobiledeveloperblog.app.ws.service.UserService;
import com.mobiledeveloperblog.app.ws.shared.Utils;
import com.mobiledeveloperblog.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;

	@Override
	public UserDto createUser(UserDto userDto) {
		
	if(userRepository.findByEmail(userDto.getEmail()) != null) throw new RuntimeException("Email already exists");
	
		UserEntity userEntity = new UserEntity();
		
		BeanUtils.copyProperties(userDto, userEntity);
		userEntity.setEncreptedPassword("Test");
		
		String publicUserID = utils.generateUserId(30);
		userEntity.setUserId(publicUserID);
		
		UserEntity storedUserDetail = userRepository.save(userEntity);
		UserDto returnValue = new UserDto();
		
		BeanUtils.copyProperties(storedUserDetail, returnValue);
		
		return returnValue;
	}

}
