package com.company;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public class EventsService {
	private List<CustomEvent> eventCache;
	private final Duration expiration = Duration.ofDays(1);

	public EventsService(List<CustomEvent> event) {
		this.eventCache = event;
	}

	private boolean isEventValid(CustomEvent event) {
		if (event.getTimestamp() != null && event.getResource() != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean put(CustomEvent event) {
		if (isEventValid(event)) {
			eventCache.add(event);
			return true;
		} else {
			System.out.println("Invalid event: " + event);
			return false;
		}
	}

	public List<CustomEvent> events() {
		List<CustomEvent> validEvents = new ArrayList<>();
		Instant currentTimestamp = Instant.now();
		for (CustomEvent event : eventCache) {
			if (Duration.between(event.getTimestamp(), currentTimestamp).compareTo(expiration) <= 0) {
				validEvents.add(event);
			}
		}
		return validEvents;
	}
}
