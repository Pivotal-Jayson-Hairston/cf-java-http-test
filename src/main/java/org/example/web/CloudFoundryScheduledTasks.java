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

	@Scheduled(initialDelayString = "${connection.delayMs:120000}", fixedRateString = "${connection.intervalMs:300000}")
	public void cloudFoundryInfo() {
		long startTime = logStart();
		CloudFoundryInfo result = cloudFoundryService.getCloudFoundryInfo();
		logEnd(startTime, result);
	}

	private long logStart() {
		log.info("Sending request to CloudController");
		return System.currentTimeMillis();
	}

	private void logEnd(long startTime, CloudFoundryInfo result) {
		long elapsedTime = System.currentTimeMillis() - startTime;
		log.info("Got response from CloudController in {} ms: {}", elapsedTime, toJsonString(result));
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
