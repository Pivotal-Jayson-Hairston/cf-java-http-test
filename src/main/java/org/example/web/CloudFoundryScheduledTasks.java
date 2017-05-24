package org.example.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.CloudFoundryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CloudFoundryScheduledTasks {
	private static final Logger log = LoggerFactory.getLogger(CloudFoundryScheduledTasks.class);

	private final CloudFoundryService cloudFoundryService;
	private final ObjectMapper objectMapper;

	public CloudFoundryScheduledTasks(CloudFoundryService cloudFoundryService, ObjectMapper objectMapper) {
		this.cloudFoundryService = cloudFoundryService;
		this.objectMapper = objectMapper;
	}

	@Scheduled(fixedRate = 1 * 60 * 1000)
	public void cloudFoundryInfo3min() {
		long startTime = logStart("1min");
		CloudFoundryInfo result = cloudFoundryService.getCloudFoundryInfo(0);
		logEnd("1min", startTime, result);
	}

	@Scheduled(fixedRate = 4 * 60 * 1000)
	public void cloudFoundryInfo6min() {
		long startTime = logStart("4min");
		CloudFoundryInfo result = cloudFoundryService.getCloudFoundryInfo(1);
		logEnd("4min", startTime, result);
	}

	@Scheduled(fixedRate = 8 * 60 * 1000)
	public void cloudFoundryInfo9min() {
		long startTime = logStart("8min");
		CloudFoundryInfo result = cloudFoundryService.getCloudFoundryInfo(2);
		logEnd("8min", startTime, result);
	}

	@Scheduled(fixedRate = 12 * 60 * 1000)
	public void cloudFoundryInfo12min() {
		long startTime = logStart("12min");
		CloudFoundryInfo result = cloudFoundryService.getCloudFoundryInfo(3);
		logEnd("12min", startTime, result);
	}

	private long logStart(String id) {
		log.info("{}: Sending request to CloudController", id);
		return System.currentTimeMillis();
	}

	private void logEnd(String id, long startTime, CloudFoundryInfo result) {
		long elapsedTime = System.currentTimeMillis() - startTime;
		log.info("{}: Got response from CloudController in {} ms: {}", id, elapsedTime, toJsonString(result));
	}

	private void log(String msg) {
		log.info("thread {}: " + msg, Thread.currentThread().getId());
	}

	private String toJsonString(CloudFoundryInfo result) {
		try {
			return objectMapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			log.info("Error creating JSON string from response: " + e.getMessage());
			return null;
		}
	}
}
