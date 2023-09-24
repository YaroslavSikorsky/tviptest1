package com.company;

public class EventValidator {
	public static boolean isEventValid(CustomEvent event) {
		if (event != null && event.getTimestamp() != null && event.getResource() != null && event.getResource().getIds() != null && event.getResource().getType() != null) {
			return true;
		} else {
			return false;
		}
	}
}
