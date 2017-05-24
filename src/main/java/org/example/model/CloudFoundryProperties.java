package org.example.model;

public class CloudFoundryProperties {
	private String target;
	private String username;
	private String password;
	private boolean trustSelfSignedCerts;
	private boolean verbose;
	private boolean debug;

	public CloudFoundryProperties(String cfApi) {
		this.target = cfApi;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isTrustSelfSignedCerts() {
		return trustSelfSignedCerts;
	}

	public void setTrustSelfSignedCerts(boolean trustSelfSignedCerts) {
		this.trustSelfSignedCerts = trustSelfSignedCerts;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}
