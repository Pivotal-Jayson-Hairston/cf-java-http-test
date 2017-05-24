package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.CloudFoundryProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	@Value("${vcap.application.cf_api:''}")
	private String cfApi;

	@Bean
	@ConfigurationProperties(prefix = "cf")
	public CloudFoundryProperties cloudFoundryProperties() {
		return new CloudFoundryProperties(cfApi);
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}
