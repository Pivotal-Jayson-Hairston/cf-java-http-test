package org.example.model;

public class CloudFoundryInfo {
	private final Object[] info;

	public CloudFoundryInfo(Object... info) {
		this.info = info;
	}

	public Object[] getInfo() {
		return info;
	}
}
