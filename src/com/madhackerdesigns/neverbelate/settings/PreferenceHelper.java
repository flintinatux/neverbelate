/**
 * 
 */
package com.madhackerdesigns.neverbelate.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Settings;

/**
 * @author flintinatux
 *
 */
public class PreferenceHelper {

	// Notification methods
	public enum NotificationMethod { ALERT, STATUS_BAR_ONLY }
	
	// Default preference values
	private static final String 	DEFAULT_ADVANCE_WARNING = "900000";
	private static final boolean 	DEFAULT_AVOID_HIGHWAYS = false;
	private static final boolean	DEFAULT_AVOID_TOLLS = false;
//	private static final boolean 	DEFAULT_FINE_REQUIRED = false;
	private static final boolean 	DEFAULT_INSISTENT = false;
	private static final String 	DEFAULT_LOCATION_FREQ = "300000";
	private static final String	 	DEFAULT_LOOKAHEAD_WINDOW = "7200000";
	private static final boolean 	DEFAULT_NEVERLATE_ENABLED = false;
	private static final String	 	DEFAULT_NOTIFICATION_METHOD = "ALERT";
	private static final String		DEFAULT_RINGTONE = Settings.System.DEFAULT_ALARM_ALERT_URI.toString();
	private static final String 	DEFAULT_SNOOZE_DURATION = "300000";
	private static final boolean 	DEFAULT_TOS_ACCEPTED = false;
	private static final String 	DEFAULT_TRAVEL_MODE = "driving";
	private static final String 	DEFAULT_TRAVELTIME_FREQ = "90000";
	private static final boolean	DEFAULT_VIBRATE = true;
	private static final boolean 	DEFAULT_WARN_NO_LOCATION = false;
	
	// Preference keys
	private final static String KEY_ADVANCE_WARNING = "advance_warning";
	private final static String KEY_AVOID_HIGHWAYS = "avoid_highways";
	private final static String KEY_AVOID_TOLLS = "avoid_tolls";
//	private final static String KEY_FINE_REQUIRED = "fine_required";
	private final static String KEY_INSISTENT = "insistent";
	private final static String KEY_LOCATION_FREQ = "location_freq";
	private final static String KEY_LOOKAHEAD_WINDOW = "lookahead_window";
	private final static String KEY_NEVERLATE_ENABLED = "neverlate_enabled";
	private final static String KEY_NOTIFICATION_METHOD = "notification_method";
	private final static String KEY_RINGTONE = "ringtone";
	private final static String KEY_SNOOZE_DURATION = "snooze_duration";
	private final static String KEY_TOS_ACCEPTED = "tos_accepted";
	private final static String KEY_TRAVEL_MODE = "travel_mode";
	private final static String KEY_TRAVELTIME_FREQ = "traveltime_freq";
	private final static String KEY_VIBRATE = "vibrate";
	private final static String KEY_WARN_NO_LOCATION = "warn_no_location";

	// Private fields
	private SharedPreferences mPrefs;
	
	public PreferenceHelper(Context applicationContext) {
		mPrefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);
	}

	/**
	 * @return True if the user wishes to avoid highways.
	 */
	public boolean isAvoidHighways() {
		return mPrefs.getBoolean(KEY_AVOID_HIGHWAYS, DEFAULT_AVOID_HIGHWAYS);
	}

	/**
	 * @return True if the user wishes to avoid toll roads.
	 */
	public boolean isAvoidTolls() {
		return mPrefs.getBoolean(KEY_AVOID_TOLLS, DEFAULT_AVOID_TOLLS);
	}

	/**
	 * @return How long in advance to warn the user to leave, in milliseconds.
	 */
	public long getAdvanceWarning() {
		return Long.valueOf(mPrefs.getString(KEY_ADVANCE_WARNING, DEFAULT_ADVANCE_WARNING));
	}

//	/**
//	 * @return True if the user wishes only to use FINE_LOCATION sources.
//	 */
//	public boolean isFineRequired() {
//		return mPrefs.getBoolean(KEY_FINE_REQUIRED, DEFAULT_FINE_REQUIRED);
//	}

	/**
	 * @return True if the users wishes to be pestered until they pay attention.
	 */
	public boolean isInsistent() {
		return mPrefs.getBoolean(KEY_INSISTENT, DEFAULT_INSISTENT);
	}
	
	/**
	 * @return The location polling frequency, in milliseconds.
	 */
	public long getLocationFreq() {
		return Long.valueOf(mPrefs.getString(KEY_LOCATION_FREQ, DEFAULT_LOCATION_FREQ));
	}

	/**
	 * @return How far ahead to search in the calendar for events, in milliseconds.
	 */
	public long getLookaheadWindow() {
		return  Long.valueOf(mPrefs.getString(KEY_LOOKAHEAD_WINDOW, DEFAULT_LOOKAHEAD_WINDOW));
	}

	/**
	 * @return True if NeverLate is enabled.
	 */
	public boolean isNeverLateEnabled() {
		return mPrefs.getBoolean(KEY_NEVERLATE_ENABLED, DEFAULT_NEVERLATE_ENABLED);
	}

	/**
	 * @param neverLateEnabled True to enable NeverLate, false to disable
	 * 
	 * @return True if NeverLate successfully enabled or disabled
	 */
	public boolean setNeverLateEnabled(boolean neverLateEnabled) {
		SharedPreferences.Editor editor = mPrefs.edit();
		return editor.putBoolean(KEY_NEVERLATE_ENABLED, neverLateEnabled).commit();
	}

	/**
	 * @return The preferred notification method, either with an ALERT, or by STATUS_BAR_ONLY.
	 */
	public NotificationMethod getNotificationMethod() {
		return Enum.valueOf(NotificationMethod.class, 
				mPrefs.getString(KEY_NOTIFICATION_METHOD, DEFAULT_NOTIFICATION_METHOD));
	}
	
	/**
	 * @return The ringtone set by the user.
	 */
	public Uri getRingtone() {
		return Uri.parse(mPrefs.getString(KEY_RINGTONE, DEFAULT_RINGTONE));
	}

	/**
	 * @return How long to snooze before warning the user again, in milliseconds.
	 */
	public long getSnoozeDuration() {
		return Long.valueOf(mPrefs.getString(KEY_SNOOZE_DURATION, DEFAULT_SNOOZE_DURATION));
	}

	/**
	 * @return True if the TOS have been accepted by the user.
	 */
	public boolean isTosAccepted() {
		return mPrefs.getBoolean(KEY_TOS_ACCEPTED, DEFAULT_TOS_ACCEPTED);
	}

	/**
	 * @param tosAccepted True to accept the TOS, false to reject.
	 * 
	 * @return True if the TOS Accepted flag is successfully set.
	 */
	public boolean setTosAccepted(boolean tosAccepted) {
		SharedPreferences.Editor editor = mPrefs.edit();
		return editor.putBoolean(KEY_TOS_ACCEPTED, tosAccepted).commit();
	}

	/**
	 * @return The preferred mode of travel, either "driving", "walking", or "bicycling".
	 */
	public String getTravelMode() {
		return mPrefs.getString(KEY_TRAVEL_MODE, DEFAULT_TRAVEL_MODE);
	}

	/**
	 * @return How frequently to check the next travel times, in milliseconds.
	 */
	public long getTraveltimeFreq() {
		return Long.valueOf(mPrefs.getString(KEY_TRAVELTIME_FREQ, DEFAULT_TRAVELTIME_FREQ));
	}

	/**
	 * @return True if vibrate is enabled.
	 */
	public boolean isVibrate() {
		return mPrefs.getBoolean(KEY_VIBRATE, DEFAULT_VIBRATE);
	}

	/**
	 * @return True if the user wishes to be warned when an upcoming event has no location specified.
	 */
	public boolean isWarnNoLocation() {
		return mPrefs.getBoolean(KEY_WARN_NO_LOCATION, DEFAULT_WARN_NO_LOCATION);
	}
}