package com.madhackerdesigns.neverlate;

/**
 * @author flintinatux
 */
public interface ServiceCommander {
	
	/**
	 * Extra key corresponding to a command to the NeverLateService.
	 */
	String EXTRA_SERVICE_COMMAND = "com.madhackerdesigns.neverlate.task";
	
	/**
	 * Command flag that instructs the NeverLateService to check the travel times to upcoming events.
	 */
	int CHECK_TRAVEL_TIMES = 0;

	/**
	 * Command flag that instructs the NeverLateService to clear all current alerts.
	 */
	int CLEAR_ALL = 1;

	/**
	 * Command flag that instructs the NeverLateService to dismiss the open alert.
	 */
	int DISMISS = 2;

	/**
	 * Command flag that instructs the NeverLateService to snooze the open alert.
	 */
	int SNOOZE = 3;

	/**
	 * Command flag that instructs the NeverLateService to notify the user immediately of the next event.
	 */
	int NOTIFY = 4;

	/**
	 * Command flag that instructs the NeverLateService to perform startup initialization activities.
	 */
	int STARTUP = 5;
	
	/**
	 * Command flag that instructs the NeverLateService to stop the insistent ringing.
	 */
	int SILENCE = 6;
}
