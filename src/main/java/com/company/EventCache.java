package com.company;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class EventCache {
	private final List<CustomEvent> eventCache = new ArrayList<>();
	private final Duration expiration;

	public EventCache(Duration expiration) {
		this.expiration = expiration;
	}

	public boolean put(CustomEvent event) {
		if (CustomEventValidator.isEventValid(event)) {
			eventCache.add(event);
			MyLogger.logInfo("Event added: " + event);
			return true;
		} else {
			MyLogger.logWarning("Invalid event: " + event);
			return false;
		}
	}

	public List<CustomEvent> getValidEvents() {
		Instant currentTimestamp = Instant.now();
		List<CustomEvent> validEvents = eventCache.stream()
				.filter(event -> Duration.between(event.getTimestamp(), currentTimestamp).compareTo(expiration) <= 0)
				.toList();
		MyLogger.logInfo("Retrieved valid events: " + validEvents.size());
		return validEvents;
	}

}
