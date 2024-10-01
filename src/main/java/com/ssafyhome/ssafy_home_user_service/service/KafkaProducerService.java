package com.ssafyhome.ssafy_home_user_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class KafkaProducerService {

	private final KafkaTemplate<String, Object> kafkaTemplate;

	@Value("${spring.kafka.max-retries}")
	private int maxRetries;

	@Value("${spring.kafka.retry-delay-ms}")
	private int retryDelayMs;

	public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {

		this.kafkaTemplate = kafkaTemplate;
	}

	public CompletableFuture<SendResult<String, Object>> sendMessage(String topic, Object message) {

		CompletableFuture<SendResult<String, Object>> future = new CompletableFuture<>();

		CompletableFuture.runAsync(() -> {
			int retries = 0;
			while (retries < maxRetries) {
				try {
					SendResult<String, Object> result = kafkaTemplate.send(topic, message).get();
					future.complete(result);
					break;
				} catch (Exception e) {
					try {
						TimeUnit.MILLISECONDS.sleep(retryDelayMs);
					} catch (InterruptedException ie) {
						Thread.currentThread().interrupt();
						future.completeExceptionally(ie);
						break;
					}
				}
			}
		});

		return future;
	}
}
