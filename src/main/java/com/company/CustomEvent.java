package com.company;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat
public class CustomEvent {
	private long timestamp;
	private Resource resource;

	@JsonCreator
	public CustomEvent(@JsonProperty("timestamp") long timestamp,
					   @JsonProperty("resource") Resource resource) {
		this.timestamp = timestamp;

	}

	public long getTimestamp() {
		return timestamp;
	}

	public Resource getResource() {
		return resource;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}
