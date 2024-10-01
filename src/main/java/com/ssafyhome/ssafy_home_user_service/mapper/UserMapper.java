package com.ssafyhome.ssafy_home_user_service.mapper;

import com.ssafyhome.ssafy_home_user_service.dto.FindInfoDto;
import com.ssafyhome.ssafy_home_user_service.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	UserEntity getUserById(long userSeq);
	UserEntity getUserByNameAndEmail(FindInfoDto findInfoDto);
	void addUser(UserEntity userEntity);
	void updatePassword(long userSeq, String password);
	void updateUser(UserEntity userEntity);
	void deleteUser(long userSeq);
}
