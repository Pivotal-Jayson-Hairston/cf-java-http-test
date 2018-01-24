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

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@Profile("java-client-v2")
public class JavaClientV2Config {
	@Bean
	public ConnectionContext connectionContext(CloudFoundryProperties cloudFoundryProperties) {
		String targetHost = getTargetURI(cloudFoundryProperties.getTarget()).getHost();
		return DefaultConnectionContext.builder()
				.apiHost(targetHost)
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

	private URI getTargetURI(String target) {
		try {
			return new URI(target);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
}
