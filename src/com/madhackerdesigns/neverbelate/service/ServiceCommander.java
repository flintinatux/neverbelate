package com.madhackerdesigns.neverbelate.service;

/**
 * @author flintinatux
 */
public interface ServiceCommander {
	
	/**
	 * Extra key corresponding to a command to the NeverBeLateService.
	 */
	String EXTRA_SERVICE_COMMAND = "com.madhackerdesigns.neverbelate.task";
	
	/**
	 * Command flag that instructs the NeverBeLateService to clear all current alerts.
	 */
	int CLEAR_ALL = 1;

	/**
	 * Command flag that instructs the NeverBeLateService to dismiss the open alert.
	 */
	int DISMISS = 2;

	/**
	 * Command flag that instructs the NeverBeLateService to snooze the open alert.
	 */
	int SNOOZE = 3;

	/**
	 * Command flag that instructs the NeverBeLateService to check the travel times to upcoming events.
	 */
	int CHECK_TRAVEL_TIMES = 4;

	/**
	 * Command flag that instructs the NeverBeLateService to notify the user immediately of the next event.
	 */
	int NOTIFY = 5;

	/**
	 * Command flag that instructs the NeverBeLateService to perform startup initialization activities.
	 */
	int STARTUP = 6;
	
	/**
	 * Command flag that instructs the NeverBeLateService to stop the insistent ringing.
	 */
	int SILENCE = 7;
}
