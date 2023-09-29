package com.company;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;


public class EventCache {
	private final List<CustomEvent> eventCache = new ArrayList<>();
	private final Duration expiration;
	private static final Logger logger = Logger.getLogger(EventCache.class.getName());
	private final Lock cacheLock = new ReentrantLock();

	public EventCache(Duration expiration) {
		this.expiration = expiration;
	}

	public boolean put(CustomEvent event) {
		try {
			cacheLock.lock();
			if (EventValidator.isEventValid(event)) {
				eventCache.add(event);
				logger.info("Event added: " + event);
				return true;
			} else {
				logger.info("Invalid event: " + event);
				return false;
			}
		} finally {
			cacheLock.unlock();
		}
	}

	public List<CustomEvent> getValidEvents() {
		try {
			cacheLock.lock();
			Instant currentTimestamp = Instant.now();
			List<CustomEvent> validEvents = eventCache.stream()
					.filter(event -> Duration.between(event.getTimestamp(), currentTimestamp).compareTo(expiration) <= 0)
					.toList();
			logger.info("Retrieved valid events: " + validEvents.size());
			return validEvents;
		} finally {
			cacheLock.unlock();
		}
	}
}