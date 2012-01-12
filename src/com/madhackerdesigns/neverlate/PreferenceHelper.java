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
	private static final long 		DEFAULT_SNOOZE_DURATION = 300000;
	private static final boolean 	DEFAULT_TOS_ACCEPTED = false;
	private static final String 	DEFAULT_TRAVEL_MODE = "driving";
	private static final long 		DEFAULT_TRAVELTIME_FREQ = 90000;
	private static final boolean	DEFAULT_VIBRATE = true;
	private static final boolean 	DEFAULT_WARN_NO_LOCATION = false;
	
	// preference keys
	private final static String KEY_ADVANCE_WARNING = "advance_warning";
	private final static String KEY_AVOID_HIGHWAYS = "avoid_highways";
	private final static String KEY_AVOID_TOLLS = "avoid_tolls";
	private final static String KEY_FINE_REQUIRED = "fine_required";
	private final static String KEY_INSISTENT = "insistent";
	private final static String KEY_LOCATION_FREQ = "location_freq";
	private final static String KEY_LOOKAHEAD_WINDOW = "lookahead_window";
	private final static String KEY_NEVERLATE_ENABLED = "neverlate_enabled";
	private final static String KEY_NOTIFICATION_METHOD = "notification_method";
	private final static String KEY_SNOOZE_DURATION = "snooze_duration";
	private final static String KEY_TOS_ACCEPTED = "tos_accepted";
	private final static String KEY_TRAVEL_MODE = "travel_mode";
	private final static String KEY_TRAVELTIME_FREQ = "traveltime_freq";
	private final static String KEY_VIBRATE = "vibrate";
	private final static String KEY_WARN_NO_LOCATION = "warn_no_location";

	// private fields
	private Context mContext;
	private SharedPreferences mPrefs;
	
	// fields for shared preferences
	private boolean avoidHighways;			// true: avoid highways
	private boolean avoidTolls;				// true: avoid tolls
	private long	advanceWarning;			// how long in advance you wish to be notified of your required departure
	private boolean fineRequired;			// false: default to coarse or last known location if fine not available
	private boolean insistent;				// true: ring and/or vibrate until noticed
	private long	locationFreq;			// how often to update the users location for accuracy
	private long 	lookaheadWindow;		// how far ahead in calendar to query for next events
	private boolean neverLateEnabled;		// whether or not NeverLate Advance Warnings are enabled
	private NotificationMethod notificationMethod;	// preferred method of notifying the user (alert, notification)
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
		advanceWarning = prefs.getLong(KEY_ADVANCE_WARNING, DEFAULT_ADVANCE_WARNING);
		avoidHighways = prefs.getBoolean(KEY_AVOID_HIGHWAYS, DEFAULT_AVOID_HIGHWAYS);
		avoidTolls = prefs.getBoolean(KEY_AVOID_TOLLS, DEFAULT_AVOID_TOLLS);
		fineRequired = prefs.getBoolean(KEY_FINE_REQUIRED, DEFAULT_FINE_REQUIRED);
		insistent = prefs.getBoolean(KEY_INSISTENT, DEFAULT_INSISTENT);
		locationFreq = prefs.getLong(KEY_LOCATION_FREQ, DEFAULT_LOCATION_FREQ);
		lookaheadWindow = prefs.getLong(KEY_LOOKAHEAD_WINDOW, DEFAULT_LOOKAHEAD_WINDOW);
		neverLateEnabled = prefs.getBoolean(KEY_NEVERLATE_ENABLED, DEFAULT_NEVERLATE_ENABLED);
		notificationMethod = Enum.valueOf(NotificationMethod.class,
				prefs.getString(KEY_NOTIFICATION_METHOD, DEFAULT_NOTIFICATION_METHOD));
		snoozeDuration = prefs.getLong(KEY_SNOOZE_DURATION, DEFAULT_SNOOZE_DURATION);
		tosAccepted = prefs.getBoolean(KEY_TOS_ACCEPTED, DEFAULT_TOS_ACCEPTED);
		travelMode = prefs.getString(KEY_TRAVEL_MODE, DEFAULT_TRAVEL_MODE);
		traveltimeFreq = prefs.getLong(KEY_TRAVELTIME_FREQ, DEFAULT_TRAVELTIME_FREQ);
		vibrate = prefs.getBoolean(KEY_VIBRATE, DEFAULT_VIBRATE);
		warnNoLocation = prefs.getBoolean(KEY_WARN_NO_LOCATION, DEFAULT_WARN_NO_LOCATION);
		mPrefs = prefs;
	}
	
	/**
	 * @return true if the preferences commit successfully
	 */
	public boolean commit() {
		SharedPreferences.Editor editor = mPrefs.edit();
		return editor.putLong(KEY_ADVANCE_WARNING, advanceWarning)
					 .putBoolean(KEY_AVOID_HIGHWAYS, avoidHighways)
					 .putBoolean(KEY_AVOID_TOLLS, avoidTolls)
					 .putBoolean(KEY_FINE_REQUIRED, fineRequired)
					 .putBoolean(KEY_INSISTENT, insistent)
					 .putLong(KEY_LOCATION_FREQ, locationFreq)
					 .putLong(KEY_LOOKAHEAD_WINDOW, lookaheadWindow)
					 .putBoolean(KEY_NEVERLATE_ENABLED, neverLateEnabled)
					 .putString(KEY_NOTIFICATION_METHOD, notificationMethod.name())
					 .putLong(KEY_SNOOZE_DURATION, snoozeDuration)
					 .putBoolean(KEY_TOS_ACCEPTED, tosAccepted)
					 .putString(KEY_TRAVEL_MODE, travelMode)
					 .putLong(KEY_TRAVELTIME_FREQ, traveltimeFreq)
					 .putBoolean(KEY_VIBRATE, vibrate)
					 .putBoolean(KEY_WARN_NO_LOCATION, warnNoLocation)
					 .commit();
	}

	/**
	 * @return the avoidHighways
	 */
	public boolean isAvoidHighways() {
		return avoidHighways;
	}

	/**
	 * @param avoidHighways the avoidHighways to set
	 */
	public void setAvoidHighways(boolean avoidHighways) {
		this.avoidHighways = avoidHighways;
	}

	/**
	 * @return the avoidTolls
	 */
	public boolean isAvoidTolls() {
		return avoidTolls;
	}

	/**
	 * @param avoidTolls the avoidTolls to set
	 */
	public void setAvoidTolls(boolean avoidTolls) {
		this.avoidTolls = avoidTolls;
	}

	/**
	 * @return the advanceWarning
	 */
	public long getAdvanceWarning() {
		return advanceWarning;
	}

	/**
	 * @param advanceWarning the advanceWarning to set
	 */
	public void setAdvanceWarning(long advanceWarning) {
		this.advanceWarning = advanceWarning;
	}

	/**
	 * @return the fineRequired
	 */
	public boolean isFineRequired() {
		return fineRequired;
	}

	/**
	 * @param fineRequired the fineRequired to set
	 */
	public void setFineRequired(boolean fineRequired) {
		this.fineRequired = fineRequired;
	}

	/**
	 * @return the insistent
	 */
	public boolean isInsistent() {
		return insistent;
	}

	/**
	 * @param insistent the insistent to set
	 */
	public void setInsistent(boolean insistent) {
		this.insistent = insistent;
	}

	/**
	 * @return the lookaheadWindow
	 */
	public long getLookaheadWindow() {
		return lookaheadWindow;
	}

	/**
	 * @param lookaheadWindow the lookaheadWindow to set
	 */
	public void setLookaheadWindow(long lookaheadWindow) {
		this.lookaheadWindow = lookaheadWindow;
	}

	/**
	 * @return the neverLateEnabled
	 */
	public boolean isNeverLateEnabled() {
		return neverLateEnabled;
	}

	/**
	 * @param neverLateEnabled the neverLateEnabled to set
	 */
	public void setNeverLateEnabled(boolean neverLateEnabled) {
		this.neverLateEnabled = neverLateEnabled;
	}

	/**
	 * @return the notificationMethod
	 */
	public NotificationMethod getNotificationMethod() {
		return notificationMethod;
	}

	/**
	 * @param notificationMethod the notificationMethod to set
	 */
	public void setNotificationMethod(NotificationMethod notificationMethod) {
		this.notificationMethod = notificationMethod;
	}

	/**
	 * @return the snoozeDuration
	 */
	public long getSnoozeDuration() {
		return snoozeDuration;
	}

	/**
	 * @param snoozeDuration the snoozeDuration to set
	 */
	public void setSnoozeDuration(long snoozeDuration) {
		this.snoozeDuration = snoozeDuration;
	}

	/**
	 * @return the tosAccepted
	 */
	public boolean isTosAccepted() {
		return tosAccepted;
	}

	/**
	 * @param tosAccepted the tosAccepted to set
	 */
	public void setTosAccepted(boolean tosAccepted) {
		this.tosAccepted = tosAccepted;
	}

	/**
	 * @return the travelMode
	 */
	public String getTravelMode() {
		return travelMode;
	}

	/**
	 * @param travelMode the travelMode to set
	 */
	public void setTravelMode(String travelMode) {
		this.travelMode = travelMode;
	}

	/**
	 * @return the traveltimeFreq
	 */
	public long getTraveltimeFreq() {
		return traveltimeFreq;
	}

	/**
	 * @param traveltimeFreq the traveltimeFreq to set
	 */
	public void setTraveltimeFreq(long traveltimeFreq) {
		this.traveltimeFreq = traveltimeFreq;
	}

	/**
	 * @return the vibrate
	 */
	public boolean isVibrate() {
		return vibrate;
	}

	/**
	 * @param vibrate the vibrate to set
	 */
	public void setVibrate(boolean vibrate) {
		this.vibrate = vibrate;
	}

	/**
	 * @return the warnNoLocation
	 */
	public boolean isWarnNoLocation() {
		return warnNoLocation;
	}

	/**
	 * @param warnNoLocation the warnNoLocation to set
	 */
	public void setWarnNoLocation(boolean warnNoLocation) {
		this.warnNoLocation = warnNoLocation;
	}
}
