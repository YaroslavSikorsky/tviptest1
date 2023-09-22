package com.company;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonFormat
public class CustomEvent {
	private long timestamp;
	private Resource resource;

	@JsonCreator
	public CustomEvent(@JsonProperty("timestamp") long timestamp,
					   @JsonProperty("resource") Resource resource) {
		this.timestamp = timestamp;
		this.resource = resource;
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

	public static class Resource {
		private String type;
		private List<String> ids;

		@JsonCreator
		public Resource(@JsonProperty("type") String type,
						@JsonProperty("ids") List<String> ids) {
			this.type = type;
			this.ids = ids;
		}

		public String getType() {
			return type;
		}

		public List<String> getIds() {
			return ids;
		}

		public void setType(String type) {
			this.type = type;
		}

		public void setIds(List<String> ids) {
			this.ids = ids;
		}

	}

}
