package com.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		CustomEvent customEvent = new CustomEvent(Instant.ofEpochMilli(1695006509000L), new Resource("CONTENT", List.of("1", "5", "9")));
		CustomEvent customEvent2 = new CustomEvent(Instant.ofEpochMilli(1695006509000L), new Resource("USER", List.of("59011", "15", "1695190408876")));

		EventsService eventsService = new EventsService();

		boolean event1Result = eventsService.put(customEvent);
		boolean event2Result = eventsService.put(customEvent2);

		System.out.println("Event1 result: " + event1Result); // true
		System.out.println("Event2 result: " + event2Result); // true

		List<CustomEvent> events = eventsService.events();
		ObjectMapper objectMapper = new ObjectMapper();


		for (CustomEvent event : events) {
			String eventJson = "";
			try {
				eventJson = objectMapper.writeValueAsString(event);
				System.out.println(eventJson);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} finally {
				System.out.println("Event: " + eventJson);
			}
		}
	}
}
