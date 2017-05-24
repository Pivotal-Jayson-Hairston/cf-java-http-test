package org.example;

import org.example.config.CertTrusterListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {
	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class)
				.listeners(new CertTrusterListener())
				.run(args);
	}
}
