package org.example.web;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.example.model.CloudFoundryInfo;
import org.example.model.CloudFoundryProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Profile("rest-template")
public class RestTemplateCloudFoundryService implements CloudFoundryService {
	private final CloudFoundryProperties properties;
	private final List<RestTemplate> restTemplates;

	public RestTemplateCloudFoundryService(CloudFoundryProperties properties, List<RestTemplate> restTemplates) {
		this.properties = properties;
		this.restTemplates = restTemplates;
	}

	@Override
	public CloudFoundryInfo getCloudFoundryInfo(int connectionIndex) {
		return new CloudFoundryInfo(getInfo(connectionIndex));
	}

	private CloudInfo getInfo(int connectionIndex) {
		return restTemplates.get(connectionIndex).getForObject(properties.getTarget() + "/v2/info", CloudInfo.class);
	}

	@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
	static class CloudInfo {
		private String name;
		private String support;
		private String build;
		private String version;
		private String description;
		private String authorizationEndpoint;
		private String tokenEndpoint;
		private String apiVersion;
		private String loggregatorEndpoint;
		private String minCliVersion;
		private String minRecommendedCliVersion;
		private String appSshEndpoint;
		private String appSshHostKeyFingerprint;
		private String appSshOauthClient;
		private String routingEndpoint;
		private String loggingEndpoint;
		private String dopplerLoggingEndpoint;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSupport() {
			return support;
		}

		public void setSupport(String support) {
			this.support = support;
		}

		public String getBuild() {
			return build;
		}

		public void setBuild(String build) {
			this.build = build;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getAuthorizationEndpoint() {
			return authorizationEndpoint;
		}

		public void setAuthorizationEndpoint(String authorizationEndpoint) {
			this.authorizationEndpoint = authorizationEndpoint;
		}

		public String getTokenEndpoint() {
			return tokenEndpoint;
		}

		public void setTokenEndpoint(String tokenEndpoint) {
			this.tokenEndpoint = tokenEndpoint;
		}

		public String getApiVersion() {
			return apiVersion;
		}

		public void setApiVersion(String apiVersion) {
			this.apiVersion = apiVersion;
		}

		public String getLoggregatorEndpoint() {
			return loggregatorEndpoint;
		}

		public void setLoggregatorEndpoint(String loggregatorEndpoint) {
			this.loggregatorEndpoint = loggregatorEndpoint;
		}

		public String getMinCliVersion() {
			return minCliVersion;
		}

		public void setMinCliVersion(String minCliVersion) {
			this.minCliVersion = minCliVersion;
		}

		public String getMinRecommendedCliVersion() {
			return minRecommendedCliVersion;
		}

		public void setMinRecommendedCliVersion(String minRecommendedCliVersion) {
			this.minRecommendedCliVersion = minRecommendedCliVersion;
		}

		public String getAppSshEndpoint() {
			return appSshEndpoint;
		}

		public void setAppSshEndpoint(String appSshEndpoint) {
			this.appSshEndpoint = appSshEndpoint;
		}

		public String getAppSshHostKeyFingerprint() {
			return appSshHostKeyFingerprint;
		}

		public void setAppSshHostKeyFingerprint(String appSshHostKeyFingerprint) {
			this.appSshHostKeyFingerprint = appSshHostKeyFingerprint;
		}

		public String getAppSshOauthClient() {
			return appSshOauthClient;
		}

		public void setAppSshOauthClient(String appSshOauthClient) {
			this.appSshOauthClient = appSshOauthClient;
		}

		public String getRoutingEndpoint() {
			return routingEndpoint;
		}

		public void setRoutingEndpoint(String routingEndpoint) {
			this.routingEndpoint = routingEndpoint;
		}

		public String getLoggingEndpoint() {
			return loggingEndpoint;
		}

		public void setLoggingEndpoint(String loggingEndpoint) {
			this.loggingEndpoint = loggingEndpoint;
		}

		public String getDopplerLoggingEndpoint() {
			return dopplerLoggingEndpoint;
		}

		public void setDopplerLoggingEndpoint(String dopplerLoggingEndpoint) {
			this.dopplerLoggingEndpoint = dopplerLoggingEndpoint;
		}
	}
}