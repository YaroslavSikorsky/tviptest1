package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		EventsService eventsService = new EventsService(Duration.ofDays(3));

		String jsonEvent1 = "{\"timestamp\": 1695190109, \"resource\": {\"type\": \"CONTENT\", \"ids\": [1, 5, 9]}}";
		String jsonEvent2 = "{\"timestamp\": 1695190109, \"resource\": {\"type\": \"USER\", \"ids\": [59011, 15, 1695190408876]}}";

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			CustomEvent event1 = objectMapper.readValue(jsonEvent1, CustomEvent.class);
			CustomEvent event2 = objectMapper.readValue(jsonEvent2, CustomEvent.class);

			eventsService.put(event1);
			eventsService.put(event2);

			List<CustomEvent> events = eventsService.events();
			for (CustomEvent event : events) {
				String eventJson = objectMapper.writeValueAsString(event);
				System.out.println("Event: " + eventJson);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
