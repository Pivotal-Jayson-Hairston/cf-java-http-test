package org.example.web;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudInfo;
import org.example.model.CloudFoundryInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("java-client")
public class JavaClientCloudFoundryService implements CloudFoundryService {
	private CloudFoundryClient client;

	@Autowired
	public JavaClientCloudFoundryService(CloudFoundryClient client) {
		this.client = client;
	}

	public CloudFoundryInfo getCloudFoundryInfo() {
		return new CloudFoundryInfo(getInfo());
	}

	private CloudInfo getInfo() {
		return client.getCloudInfo();
	}
}
