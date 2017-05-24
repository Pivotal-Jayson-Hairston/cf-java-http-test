package org.example.web;

import org.cloudfoundry.client.CloudFoundryClient;
import org.cloudfoundry.client.v2.info.GetInfoRequest;
import org.cloudfoundry.client.v2.info.GetInfoResponse;
import org.example.model.CloudFoundryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("java-client-v2")
public class JavaClientV2CloudFoundryService implements CloudFoundryService {
	private List<CloudFoundryClient> clients;

	@Autowired
	public JavaClientV2CloudFoundryService(List<CloudFoundryClient> clients) {
		this.clients = clients;
	}

	public CloudFoundryInfo getCloudFoundryInfo(int connectionIndex) {
		return new CloudFoundryInfo(getInfo(connectionIndex));
	}

	private GetInfoResponse getInfo(int connectionIndex) {
		return clients.get(connectionIndex)
				.info()
				.get(GetInfoRequest.builder().build())
				.block();
	}
}
