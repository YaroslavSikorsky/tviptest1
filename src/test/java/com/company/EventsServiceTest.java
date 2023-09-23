package com.company;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class EventsServiceTest {

	private EventsService eventsService;

	@BeforeEach
	public void setUp() {
		eventsService = new EventsService(Duration.ofDays(4));
	}

	@DisplayName("Test EventService.get()")
	@Test
	public void testPutValidEvent() {

		String jsonEvent1 = "{\"timestamp\": 1695190109000, \"resource\": {\"type\": \"CONTENT\", \"ids\": [1, 5, 9]}}";
		String jsonEvent2 = "{\"timestamp\": 1695190109000, \"resource\": {\"type\": \"USER\", \"ids\": [59011, 15, 1695190408876]}}";

		ObjectMapper objectMapper = new ObjectMapper()
				.registerModule(new JavaTimeModule())
				.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
				.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
				.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		try {
			CustomEvent event1 = objectMapper.readValue(jsonEvent1, CustomEvent.class);
			CustomEvent event2 = objectMapper.readValue(jsonEvent2, CustomEvent.class);

			eventsService.put(event1);
			eventsService.put(event2);

			List<CustomEvent> events = eventsService.events();
			assertEquals(2, events.size());
			for (CustomEvent event : events) {
				String eventJson = objectMapper.writeValueAsString(event);
				System.out.println("Event: " + eventJson);
			}
		} catch (IOException e) {
			fail("IOException occurred: " + e.getMessage());
		}
	}
}
