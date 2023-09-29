package com.company;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventServiceTest {
	private final EventServiceWrapper eventServiceWrapper = new EventServiceWrapper(new EventService(Duration.ofHours(300)));
	private static final Logger logger = Logger.getLogger(EventServiceTest.class.getName());

	@Test()
	public void happyPath() {

		String validEvent1 = "{\"timestamp\": 1695190109000, \"resource\": {\"type\": \"CONTENT\", \"ids\": [1, 5, 9]}}";
		String validEvent2 = "{\"timestamp\": 1695190109000, \"resource\": {\"type\": \"USER\", \"ids\": [59011, 15, 1695190408876]}}";
		String expiredEvent = "{\"timestamp\": 1685180007000,	\"resource\": {\"type\": \"CONTENT\", \"ids\": [1, 5, 9]}}";
		String eventWithNullType = "{\"timestamp\": 1695190109000, \"resource\": {\"ids\": [59011, 15, 1695190408876]}}";

		eventServiceWrapper.put(validEvent1);
		eventServiceWrapper.put(validEvent2);
		eventServiceWrapper.put(expiredEvent);
		eventServiceWrapper.put(eventWithNullType);

		List<String> validEvents = eventServiceWrapper.events();
		assertEquals(2, validEvents.size());
		for (String validEvent : validEvents) {
			logger.info("Event: " + validEvent);
		}

	}
}
