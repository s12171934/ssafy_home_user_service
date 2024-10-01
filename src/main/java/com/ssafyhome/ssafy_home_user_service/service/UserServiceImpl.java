package com.ssafyhome.ssafy_home_user_service.service;

import com.ssafyhome.ssafy_home_user_service.dto.FindInfoDto;
import com.ssafyhome.ssafy_home_user_service.entity.EmailSecretEntity;
import com.ssafyhome.ssafy_home_user_service.entity.UserEntity;
import com.ssafyhome.ssafy_home_user_service.mapper.UserMapper;
import com.ssafyhome.ssafy_home_user_service.repository.EmailSecretRepository;
import com.ssafyhome.ssafy_home_user_service.util.MailHandler;
import com.ssafyhome.ssafy_home_user_service.util.SecretUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	private final EmailSecretRepository emailSecretRepository;
	private final MailHandler mailHandler;
	private final SecretUtil secretUtill;
	private final KafkaProducerService kafkaProducerService;
	private final BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(
			UserMapper userMapper,
			EmailSecretRepository emailSecretRepository,
			MailHandler mailHandler,
			SecretUtil secretUtil,
			KafkaProducerService kafkaProducerService,
			BCryptPasswordEncoder passwordEncoder
	) {

		this.userMapper = userMapper;
		this.emailSecretRepository = emailSecretRepository;
		this.mailHandler = mailHandler;
		this.secretUtill = secretUtil;
		this.kafkaProducerService = kafkaProducerService;
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Override
	public ResponseEntity<?> getUserInfo(long id) {

		UserEntity userEntity = userMapper.getUserById(id);
		return new ResponseEntity<>(userEntity, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> registerUser(UserEntity userEntity) {

		userMapper.addUser(userEntity);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> findId(FindInfoDto findInfoDto) {

		UserEntity userEntity = userMapper.getUserByNameAndEmail(findInfoDto);
		return new ResponseEntity<>(userEntity, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> findPassword(FindInfoDto findInfoDto) {

		UserEntity userEntity = userMapper.getUserByNameAndEmail(findInfoDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> checkMail(EmailSecretEntity emailSecretEntity) {

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> changePassword(long id, String password) {

		userMapper.updatePassword(id, passwordEncoder.encode(password));
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> updateUserInfo(long id, UserEntity userEntity) {

		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		userMapper.updateUser(userEntity);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteUserInfo(long id) {

		userMapper.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
