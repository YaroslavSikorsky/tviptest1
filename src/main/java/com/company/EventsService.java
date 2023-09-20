package com.company;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EventsService {
	private final List<CustomEvent> eventCache;
	private final Duration expiration = Duration.ofDays(1);

	public EventsService() {
		this.eventCache = new ArrayList<>();
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
//			System.out.println(eventCache.get(0).getResource().getIds().toString());
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
