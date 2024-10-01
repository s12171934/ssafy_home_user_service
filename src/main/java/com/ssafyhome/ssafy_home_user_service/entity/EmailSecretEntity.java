package com.ssafyhome.ssafy_home_user_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash(value = "EmailSecret", timeToLive = 5 * 60)
public class EmailSecretEntity {

	@Id
	private String userSeq;
	private String secret;
}
