package org.example.config;

import org.cloudfoundry.client.CloudFoundryClient;
import org.cloudfoundry.reactor.ConnectionContext;
import org.cloudfoundry.reactor.DefaultConnectionContext;
import org.cloudfoundry.reactor.client.ReactorCloudFoundryClient;
import org.example.model.CloudFoundryProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import reactor.core.publisher.Mono;

@Configuration
@Profile("java-client-v2")
public class JavaClientV2Config {
	@Bean
	public ConnectionContext connectionContext(CloudFoundryProperties cloudFoundryProperties) {
		return DefaultConnectionContext.builder()
				.apiHost(cloudFoundryProperties.getTarget())
				.skipSslValidation(cloudFoundryProperties.isTrustSelfSignedCerts())
				.build();
	}

	@Bean
	public CloudFoundryClient cloudFoundryClient(ConnectionContext connectionContext) {
		return ReactorCloudFoundryClient.builder()
				.connectionContext(connectionContext)
				.tokenProvider(connContext ->  Mono.just(""))
				.build();
	}
}
