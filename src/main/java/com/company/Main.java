package com.company;

import java.time.Instant;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		System.out.println("start");

		CustomEvent customEvent = new CustomEvent(Instant.ofEpochSecond(1695006509000), new Resource("CONTENT", List.of("1", "5", "9"));
		CustomEvent customEvent2 = new CustomEvent(Instant.ofEpochSecond(1695006509000), new Resource("USER", List.of("59011", "15", "1695190408876"));

		EventsService eventsService = new EventsService(List.of(customEvent, customEvent2));

		//ObjectMapper.writeValue();
	}
}
