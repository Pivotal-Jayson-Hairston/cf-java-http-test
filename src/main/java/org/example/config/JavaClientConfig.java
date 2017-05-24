package org.example.config;

import org.cloudfoundry.client.lib.CloudCredentials;
import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.example.model.CloudFoundryProperties;
import org.example.web.SampleRestLogCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("java-client")
public class JavaClientConfig {

	@Bean
	public List<CloudFoundryClient> cloudFoundryClient(CloudFoundryProperties cloudFoundryProperties) {
		List<CloudFoundryClient> clients = new ArrayList<>();
		for (int i = 0; i < cloudFoundryProperties.getConnectionCount(); i++) {
			clients.add(getCloudFoundryClient(cloudFoundryProperties));
		}
		return clients;
	}

	private CloudFoundryClient getCloudFoundryClient(CloudFoundryProperties cloudFoundryProperties) {
		CloudCredentials credentials = getCloudCredentials(cloudFoundryProperties);

		CloudFoundryClient client = new CloudFoundryClient(credentials,
				getTargetURL(cloudFoundryProperties.getTarget()),
				cloudFoundryProperties.isTrustSelfSignedCerts());

		if (cloudFoundryProperties.isVerbose()) {
			client.registerRestLogListener(new SampleRestLogCallback());
		}

		if (cloudFoundryProperties.getUsername() != null) {
			client.login();
		}

		return client;
	}

	private CloudCredentials getCloudCredentials(CloudFoundryProperties cloudFoundryProperties) {
		return new CloudCredentials(cloudFoundryProperties.getUsername(), cloudFoundryProperties.getPassword());
	}

	private URL getTargetURL(String target) {
		try {
			return getTargetURI(target).toURL();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	private URI getTargetURI(String target) {
		try {
			return new URI(target);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
}
