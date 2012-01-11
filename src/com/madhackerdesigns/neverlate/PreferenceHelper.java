/**
 * 
 */
package com.madhackerdesigns.neverlate;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

/**
 * @author flintinatux
 *
 */
public class PreferenceHelper {

	// Enum to choose between notification methods
	public enum NotificationMethod { ALERT, NOTIFICATION };
	
	// default preference values
	private static final long 		DEFAULT_ADVANCE_WARNING = 900000;
	private static final boolean 	DEFAULT_AVOID_HIGHWAYS = false;
	private static final boolean	DEFAULT_AVOID_TOLLS = false;
	private static final boolean 	DEFAULT_FINE_REQUIRED = false;
	private static final boolean 	DEFAULT_INSISTENT = false;
	private static final long 		DEFAULT_LOCATION_FREQ = 300000;
	private static final long	 	DEFAULT_LOOKAHEAD_WINDOW = 7200000;
	private static final boolean 	DEFAULT_NEVERLATE_ENABLED = false;
	private static final String 	DEFAULT_NOTIFICATION_METHOD = "ALERT";
//	private static final String 	DEFAULT_RINGTONE = ;		// TODO: not sure how to implement
	private static final long 		DEFAULT_SNOOZE_DURATION = 300000;
	private static final boolean 	DEFAULT_TOS_ACCEPTED = false;
	private static final String 	DEFAULT_TRAVEL_MODE = "driving";
	private static final long 		DEFAULT_TRAVELTIME_FREQ = 90000;
	private static final boolean	DEFAULT_VIBRATE = true;
	private static final boolean 	DEFAULT_WARN_NO_LOCATION = false;

	// application context
	private Context mContext;
	
	// fields for shared preferences
	private boolean avoidHighways;			// true: avoid highways
	private boolean avoidTolls;				// true: avoid tolls
	private long	advanceWarning;			// how long in advance you wish to be notified of your required departure
	private boolean fineRequired;			// false: default to coarse or last known location if fine not available
	private boolean insistent;				// true: ring and/or vibrate until noticed
	private long 	lookaheadWindow;		// how far ahead in calendar to query for next events
	private boolean neverLateEnabled;		// whether or not NeverLate Advance Warnings are enabled
	private NotificationMethod notificationMethod;	// preferred method of notifying the user (alert, notification)
//	private String 	ringtone;				// preferred alarm ringtone (TODO: not sure how to implement)
	private long 	snoozeDuration;			// how long to snooze before warning again
	private boolean tosAccepted;			// false: whether user has accepted the TOS
	private String 	travelMode;				// preferred mode of travel (driving, biking, walking)
	private long 	traveltimeFreq;			// how often to check travel time to next events
	private boolean vibrate;				// true: vibrate on notify
	private boolean warnNoLocation;			// true: may add a valid location or disable notifications for that event

	public PreferenceHelper(Context applicationContext) {
		mContext = applicationContext;
		reloadPreferences();
	}

	public void reloadPreferences() {
		// lookup application preferences
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
		neverLateEnabled = prefs.getBoolean("neverlate_enabled", DEFAULT_NEVERLATE_ENABLED);
		traveltimeFreq = prefs.getLong("traveltime_freq", DEFAULT_TRAVELTIME_FREQ);
		advanceWarning = prefs.getLong("advance_warning", DEFAULT_ADVANCE_WARNING);
		fineRequired = prefs.getBoolean("fine_required", DEFAULT_FINE_REQUIRED);
		lookaheadWindow = prefs.getLong("lookahead_window", DEFAULT_LOOKAHEAD_WINDOW);
		warnNoLocation = prefs.getBoolean("warn_no_location", DEFAULT_WARN_NO_LOCATION);
		travelMode = prefs.getString("travel_mode", DEFAULT_TRAVEL_MODE);
		avoidHighways = prefs.getBoolean("avoid_highways", DEFAULT_AVOID_HIGHWAYS);
		avoidTolls = prefs.getBoolean("avoid_tolls", DEFAULT_AVOID_TOLLS);
		notificationMethod = Enum.valueOf(NotificationMethod.class,
				prefs.getString("notification_method", DEFAULT_NOTIFICATION_METHOD));
		snoozeDuration = prefs.getLong("snooze_duration", DEFAULT_SNOOZE_DURATION);
//		ringtone = prefs.getString("ringtone", DEFAULT_RINGTONE);		// TODO: not sure how to implement yet
		vibrate = prefs.getBoolean("vibrate", DEFAULT_VIBRATE);
		insistent = prefs.getBoolean("insistent", DEFAULT_INSISTENT);
		tosAccepted = prefs.getBoolean("tos_accepted", DEFAULT_TOS_ACCEPTED);
	}
}
