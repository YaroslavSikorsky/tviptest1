package com.company;

import java.time.Duration;
import java.util.List;

public class EventService {
	private final EventCache eventCache;

	public EventService() {
		this(Duration.ofHours(24));
	}

	public EventService(Duration expiration) {
		this.eventCache = new EventCache(expiration);
	}

	public boolean put(CustomEvent event) {
		return eventCache.put(event);
	}

	public List<CustomEvent> events() {
		return eventCache.getValidEvents();
	}
}
