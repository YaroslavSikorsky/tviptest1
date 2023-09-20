package com.company;

import java.time.Instant;

public class CustomEvent {
	private Instant timestamp;
	private Resource resource;

	public CustomEvent(Instant timestamp, Resource resource) {
		this.timestamp = timestamp;
		this.resource = resource;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public Resource getResource() {
		return resource;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
