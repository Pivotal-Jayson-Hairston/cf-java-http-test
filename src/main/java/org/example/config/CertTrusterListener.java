package org.example.config;

import io.pivotal.springcloud.ssl.SslCertificateTruster;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

import java.net.MalformedURLException;
import java.net.URL;

public class CertTrusterListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
	@Override
	public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
		String cfTarget = event.getEnvironment().getProperty("cf.target");
		if (cfTarget != null) {
			try {
				URL cfTargetUrl = new URL(cfTarget);
				String host = cfTargetUrl.getHost();
				if ("https".equals(cfTargetUrl.getProtocol()) && host != null) {
					int httpsPort = cfTargetUrl.getPort() > 0 ? cfTargetUrl.getPort() : 443;
					try {
						SslCertificateTruster.trustCertificate(host, httpsPort, 5000);
					} catch (Exception e) {
						System.err.println("trusting certificate at " + host + ":" + httpsPort + " failed due to " + e);
						e.printStackTrace();
					}
				}
			} catch (MalformedURLException e1) {
				System.err.println("Cannot parse CF_TARGET '" + cfTarget + "' as a URL");
			}
		}
	}
}
