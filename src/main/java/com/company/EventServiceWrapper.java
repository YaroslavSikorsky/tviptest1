package com.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class EventServiceWrapper {
	private final EventService eventService;
	private final ObjectMapper objectMapper;

	public EventServiceWrapper(EventService eventService) {
		this.eventService = eventService;
		this.objectMapper = CustomObjectMapper.createObjectMapper();
	}

	public boolean put(String eventJson) {
		try {
			CustomEvent event = objectMapper.readValue(eventJson, CustomEvent.class);
			return eventService.put(event);
		} catch (Exception e) {
			return false;
		}
	}

	public List<String> events() {
		List<CustomEvent> customEvents = eventService.events();
		List<String> eventJsonList = new ArrayList<>();

		for (CustomEvent event : customEvents) {
			try {
				String eventJson = objectMapper.writeValueAsString(event);
				eventJsonList.add(eventJson);
			} catch (JsonProcessingException e) {
			}
		}
		return eventJsonList;
	}
}
