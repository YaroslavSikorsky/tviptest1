package com.company;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Resource {
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
