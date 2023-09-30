package com.company;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;


public class EventCache {
	private final List<CustomEvent> eventCache = new ArrayList<>();
	private final Duration expiration;
	private static final Logger logger = Logger.getLogger(EventCache.class.getName());
	private final ReadWriteLock cacheLock = new ReentrantReadWriteLock();

	public EventCache(Duration expiration) {
		this.expiration = expiration;
	}

	public boolean put(CustomEvent event) {
		cacheLock.writeLock().lock();
		try {
			if (EventValidator.isEventValid(event)) {
				eventCache.add(event);
				logger.info("Event added: " + event);
				return true;
			} else {
				logger.info("Invalid event: " + event);
				return false;
			}
		} finally {
			cacheLock.writeLock().unlock();
		}
	}

	public List<CustomEvent> getValidEvents() {
		cacheLock.readLock().lock();
		try {
			Instant currentTimestamp = Instant.now();
			List<CustomEvent> validEvents = eventCache.stream()
					.filter(event -> Duration.between(event.getTimestamp(), currentTimestamp).compareTo(expiration) <= 0)
					.toList();
			logger.info("Retrieved valid events: " + validEvents.size());
			return validEvents;
		} finally {
			cacheLock.readLock().unlock();
		}
	}
}