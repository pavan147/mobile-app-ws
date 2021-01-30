package com.mobiledeveloperblog.app.ws.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobiledeveloperblog.app.ws.UserRepository;
import com.mobiledeveloperblog.app.ws.io.entity.UserEntity;
import com.mobiledeveloperblog.app.ws.service.UserService;
import com.mobiledeveloperblog.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
	
		UserEntity userEntity = new UserEntity();
		
		BeanUtils.copyProperties(userDto, userEntity);
		userEntity.setEncreptedPassword("Test");
		userEntity.setUserId("testUserID");
		
		UserEntity storedUserDetail = userRepository.save(userEntity);
		UserDto returnValue = new UserDto();
		
		BeanUtils.copyProperties(storedUserDetail, returnValue);
		
		return returnValue;
	}

}
