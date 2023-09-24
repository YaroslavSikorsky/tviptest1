package com.company;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventServiceTest {
	private final EventServiceWrapper eventServiceWrapper = new EventServiceWrapper(new EventService(Duration.ofDays(5)));
	private static final Logger logger = Logger.getLogger(EventServiceTest.class.getName());

	@Test()
	public void  happyPath() {

		String jsonEvent1 = "{\"timestamp\": 1695190109000, \"resource\": {\"type\": \"CONTENT\", \"ids\": [1, 5, 9]}}";
		String jsonEvent2 = "{\"timestamp\": 1695190109000, \"resource\": {\"type\": \"USER\", \"ids\": [59011, 15, 1695190408876]}}";

		eventServiceWrapper.put(jsonEvent1);
		eventServiceWrapper.put(jsonEvent2);

		List<String> eventJsonList = eventServiceWrapper.events();
		assertEquals(2, eventJsonList.size());
		for (String eventJson : eventJsonList) {
			logger.info("Event: " + eventJson);
		}

	}
}
