package com.ssafyhome.ssafy_home_user_service.controller;

import com.ssafyhome.ssafy_home_user_service.dto.FindInfoDto;
import com.ssafyhome.ssafy_home_user_service.entity.EmailSecretEntity;
import com.ssafyhome.ssafy_home_user_service.entity.UserEntity;
import com.ssafyhome.ssafy_home_user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {

		this.userService = userService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserInfo(
			@PathVariable
			long id
	) {

		return userService.getUserInfo(id);
	}

	@PostMapping("/")
	public ResponseEntity<?> registerUser(
			@RequestBody
			UserEntity userEntity
	) {

		return userService.registerUser(userEntity);
	}

	@PostMapping("/find/{type}")
	public ResponseEntity<?> findUserInfo(
			@PathVariable
			String type,

			@RequestBody
			FindInfoDto findInfoDto
	) {

		return type.equals("id") ?
				userService.findId(findInfoDto) :
				userService.findPassword(findInfoDto);
	}

	@PostMapping("/check-mail")
	public ResponseEntity<?> checkMailSecret(
			@RequestBody
			EmailSecretEntity emailSecretEntity
	) {

		return userService.checkMail(emailSecretEntity);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> changePassword(
			@PathVariable
			long id,

			@RequestBody
			String password
	) {

		return userService.changePassword(id, password);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUserInfo(
			@PathVariable
			long id,

			@RequestBody
			UserEntity userEntity
	) {

		return userService.updateUserInfo(id, userEntity);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUserInfo(
			@PathVariable
			long id
	) {

		return userService.deleteUserInfo(id);
	}
}
