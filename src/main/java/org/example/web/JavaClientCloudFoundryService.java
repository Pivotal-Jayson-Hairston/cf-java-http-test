package org.example.web;

import java.util.List;

import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.cloudfoundry.client.lib.domain.CloudInfo;
import org.example.model.CloudFoundryInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("java-client")
public class JavaClientCloudFoundryService implements CloudFoundryService {
	private List<CloudFoundryClient> clients;

	@Autowired
	public JavaClientCloudFoundryService(List<CloudFoundryClient> clients) {
		this.clients = clients;
	}

	public CloudFoundryInfo getCloudFoundryInfo(int connectionIndex) {
		return new CloudFoundryInfo(getInfo(connectionIndex));
	}

	private CloudInfo getInfo(int connectionIndex) {
		return clients.get(connectionIndex).getCloudInfo();
	}
}
