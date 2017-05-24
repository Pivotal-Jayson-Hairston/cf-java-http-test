package org.example.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("rest-template")
public class RestTemplateConfig {
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		CloseableHttpClient httpClient = createHttpClient();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
		return restTemplate;
	}

	private CloseableHttpClient createHttpClient() {
		return HttpClientBuilder
				.create()
//				.setConnectionReuseStrategy(new NoConnectionReuseStrategy())
//				.setKeepAliveStrategy((response, context) -> 4 * 60 * 1000)
				.build();
	}
}
