package org.example.web;

import org.cloudfoundry.client.CloudFoundryClient;
import org.cloudfoundry.client.v2.info.GetInfoRequest;
import org.cloudfoundry.client.v2.info.GetInfoResponse;
import org.example.model.CloudFoundryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("java-client-v2")
public class JavaClientV2CloudFoundryService implements CloudFoundryService {
	private CloudFoundryClient client;

	@Autowired
	public JavaClientV2CloudFoundryService(CloudFoundryClient client) {
		this.client = client;
	}

	public CloudFoundryInfo getCloudFoundryInfo() {
		return new CloudFoundryInfo(getInfo());
	}

	private GetInfoResponse getInfo() {
		return client
				.info()
				.get(GetInfoRequest.builder().build())
				.block();
	}
}
