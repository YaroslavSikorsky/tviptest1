package com.company;


public class EventValidator {
	public static boolean isEventValid(CustomEvent event) {
		return event != null
				&& event.getTimestamp() != null
				&& event.getResource() != null
				&& event.getResource().getIds() != null
				&& event.getResource().getType() != null;
	}
}
