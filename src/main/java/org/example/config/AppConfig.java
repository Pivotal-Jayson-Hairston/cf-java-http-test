package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.CloudFoundryProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	@Bean
	@ConfigurationProperties(prefix = "cf")
	public CloudFoundryProperties cloudFoundryProperties() {
		return new CloudFoundryProperties();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}
