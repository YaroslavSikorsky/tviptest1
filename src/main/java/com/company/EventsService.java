package com.company;

import java.time.Duration;
import java.util.List;

public class EventsService {
	private final EventCache eventCache;

	public EventsService() {
		this(Duration.ofDays(1));
	}

	public EventsService(Duration expiration) {
		this.eventCache = new EventCache(expiration);
	}

	public boolean put(CustomEvent event) {
		return eventCache.put(event);
	}

	public List<CustomEvent> events() {
		return eventCache.getValidEvents();
	}
}
