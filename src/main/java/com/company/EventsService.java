package com.company;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class EventsService {
	private final List<CustomEvent> eventCache;
	private final Duration expiration;

	public EventsService() {
		this.eventCache = new ArrayList<>();
		this.expiration = Duration.ofDays(1);
	}

	public EventsService(Duration expiration) {
		this.eventCache = new ArrayList<>();
		this.expiration = expiration;
	}

	private boolean isEventValid(CustomEvent event) {
		if (event != null && event.getTimestamp() != 0 && event.getResource() != null && event.getResource().getIds() != null && event.getResource().getType() != null) {
			return false;
		} else {
			return true;
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
		Instant currentTimestamp = Instant.now();
		return eventCache.stream()
				.filter(event -> Duration.between(Instant.ofEpochSecond(event.getTimestamp()), currentTimestamp).compareTo(expiration) <= 0)
				.collect(Collectors.toList());


	}
}
