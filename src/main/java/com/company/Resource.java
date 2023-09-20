package com.company;

import java.util.List;

public class Resource {
	private String type;
	private List<String> ids;

	public Resource(String type, List<String> ids) {
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
