package com.ssafyhome.ssafy_home_user_service.service;

import com.ssafyhome.ssafy_home_user_service.dto.FindInfoDto;
import com.ssafyhome.ssafy_home_user_service.entity.EmailSecretEntity;
import com.ssafyhome.ssafy_home_user_service.entity.UserEntity;
import org.springframework.http.ResponseEntity;

public interface UserService {

	ResponseEntity<?> getUserInfo(long id);
	ResponseEntity<?> registerUser(UserEntity userEntity);
	ResponseEntity<?> findId(FindInfoDto findInfoDto);
	ResponseEntity<?> findPassword(FindInfoDto findInfoDto);
	ResponseEntity<?> checkMail(EmailSecretEntity emailSecretEntity);
	ResponseEntity<?> changePassword(long id, String password);
	ResponseEntity<?> updateUserInfo(long id, UserEntity userEntity);
	ResponseEntity<?> deleteUserInfo(long id);
}
