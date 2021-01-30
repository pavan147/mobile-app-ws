package com.mobiledeveloperblog.app.ws.serviceimpl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mobiledeveloperblog.app.ws.io.entity.UserEntity;
import com.mobiledeveloperblog.app.ws.io.repository.UserRepository;
import com.mobiledeveloperblog.app.ws.service.UserService;
import com.mobiledeveloperblog.app.ws.shared.Utils;
import com.mobiledeveloperblog.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {

		if (userRepository.findByEmail(userDto.getEmail()) != null)
			throw new RuntimeException("Email already exists");

		UserEntity userEntity = new UserEntity();

		BeanUtils.copyProperties(userDto, userEntity);
		userEntity.setEncreptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

		String publicUserID = utils.generateUserId(30);
		userEntity.setUserId(publicUserID);

		UserEntity storedUserDetail = userRepository.save(userEntity);
		UserDto returnValue = new UserDto();

		BeanUtils.copyProperties(storedUserDetail, returnValue);

		return returnValue;
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if (userEntity == null) {

			throw new UsernameNotFoundException(email);
		}
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(userEntity, dto); 
		return dto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null) {

			throw new UsernameNotFoundException(email);
		}

		return new User(email, userEntity.getEncreptedPassword(), new ArrayList<>());
	}

}
