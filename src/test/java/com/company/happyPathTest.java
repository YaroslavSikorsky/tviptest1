package com.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class happyPathTest {
	private final EventServiceWrapper eventServiceWrapper = new EventServiceWrapper(new EventService(Duration.ofDays(1)));

	@Test()
	public void testPutValidEvent() {

		String jsonEvent1 = "{\"timestamp\": 1695190109000, \"resource\": {\"type\": \"CONTENT\", \"ids\": [1, 5, 9]}}";
		String jsonEvent2 = "{\"timestamp\": 1695190109000, \"resource\": {\"type\": \"USER\", \"ids\": [59011, 15, 1695190408876]}}";

		eventServiceWrapper.put(jsonEvent1);
		eventServiceWrapper.put(jsonEvent2);

		List<String> eventJsonList = eventServiceWrapper.events();
		assertEquals(2, eventJsonList.size());
		for (String eventJson : eventJsonList) {
			System.out.println("Event: " + eventJson);
		}

	}
}
