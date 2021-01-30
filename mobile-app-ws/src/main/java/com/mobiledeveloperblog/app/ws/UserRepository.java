package com.mobiledeveloperblog.app.ws;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mobiledeveloperblog.app.ws.io.entity.UserEntity;
import java.lang.String;
 

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	UserEntity findByEmail(String email);

}
