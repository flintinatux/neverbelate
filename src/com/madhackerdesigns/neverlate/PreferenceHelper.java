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
	
	// application context
	private Context mContext;
	
	// fields for shared preferences
	public boolean mNeverLateEnabled;		// whether or not NeverLate Advance Warnings are enabled
	public long mTraveltimeFreq;			// how often to check travel time to next events
	public long mAdvanceWarning;			// how long in advance you wish to be notified of your required departure
	public boolean mFineRequired;			// false: default to coarse or last known location if fine not available
	public long mLookaheadWindow;			// how far ahead in calendar to query for next events
	public boolean mWarnNoLocation;			// true: may add a valid location or disable notifications for that event
	public String mTravelMode;				// preferred mode of travel (driving, biking, walking)
	public boolean mAvoidHighways;			// true: avoid highways
	public boolean mAvoidTolls;				// true: avoid tolls
	public NotificationMethod mNotificationMethod;	// preferred method of notifying the user (alert, notification)
	public long mSnoozeDuration;			// how long to snooze before warning again
//	public String mRingtone;				// preferred alarm ringtone (TODO: not sure how to implement)
	public boolean mVibrate;				// true: vibrate on notify
	public boolean mInsistent;				// true: ring and/or vibrate until noticed
	public boolean mTosAccepted;			// false: whether user has accepted the TOS

	public PreferenceHelper(Context applicationContext) {
		mContext = applicationContext;
		reloadPreferences();
	}

	public void reloadPreferences() {
		// lookup default settings values from resources
		Resources resources = mContext.getResources();
		final boolean DEFAULT_NEVERLATE_ENABLED = resources.getBoolean(R.bool.default_neverlate_enabled);
		final String DEFAULT_TRAVELTIME_FREQ = resources.getString(R.string.default_traveltime_freq);
		final String DEFAULT_ADVANCE_WARNING = resources.getString(R.string.default_advance_warning);
		final boolean DEFAULT_FINE_REQUIRED = resources.getBoolean(R.bool.default_fine_required);
		final String DEFAULT_LOOKAHEAD_WINDOW = resources.getString(R.string.default_lookahead_window);
		final boolean DEFAULT_WARN_NO_LOCATION = resources.getBoolean(R.bool.default_warn_no_location);
		final String DEFAULT_TRAVEL_MODE = resources.getString(R.string.default_travel_mode);
		final boolean DEFAULT_AVOID_HIGHWAYS = resources.getBoolean(R.bool.default_avoid_highways);
		final boolean DEFAULT_AVOID_TOLLS = resources.getBoolean(R.bool.default_avoid_tolls);
		final String DEFAULT_NOTIFICATION_METHOD = resources.getString(R.string.default_notification_method);
		final String DEFAULT_SNOOZE_DURATION = resources.getString(R.string.default_snooze_duration);
//		final String DEFAULT_RINGTONE = resources.getString(id);		// TODO: not sure how to implement
		final boolean DEFAULT_VIBRATE = resources.getBoolean(R.bool.default_vibrate);
		final boolean DEFAULT_INSISTENT = resources.getBoolean(R.bool.default_insistent);
		final boolean DEFAULT_TOS_ACCEPTED = resources.getBoolean(R.bool.default_tos_accepted);
		
		// lookup application preferences
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
		mNeverLateEnabled = prefs.getBoolean("neverlate_enabled", DEFAULT_NEVERLATE_ENABLED);
		mTraveltimeFreq = Long.parseLong(prefs.getString("traveltime_freq", DEFAULT_TRAVELTIME_FREQ));
		mAdvanceWarning = Long.parseLong(prefs.getString("advance_warning", DEFAULT_ADVANCE_WARNING));
		mFineRequired = prefs.getBoolean("fine_required", DEFAULT_FINE_REQUIRED);
		mLookaheadWindow = Long.parseLong(prefs.getString("lookahead_window", DEFAULT_LOOKAHEAD_WINDOW));
		mWarnNoLocation = prefs.getBoolean("warn_no_location", DEFAULT_WARN_NO_LOCATION);
		mTravelMode = prefs.getString("travel_mode", DEFAULT_TRAVEL_MODE);
		mAvoidHighways = prefs.getBoolean("avoid_highways", DEFAULT_AVOID_HIGHWAYS);
		mAvoidTolls = prefs.getBoolean("avoid_tolls", DEFAULT_AVOID_TOLLS);
		mNotificationMethod = Enum.valueOf(NotificationMethod.class,
				prefs.getString("notification_method", DEFAULT_NOTIFICATION_METHOD));
		mSnoozeDuration = Long.parseLong(prefs.getString("snooze_duration", DEFAULT_SNOOZE_DURATION));
//		mRingtone = prefs.getString("ringtone", DEFAULT_RINGTONE);		// TODO: not sure how to implement yet
		mVibrate = prefs.getBoolean("vibrate", DEFAULT_VIBRATE);
		mInsistent = prefs.getBoolean("insistent", DEFAULT_INSISTENT);
		mTosAccepted = prefs.getBoolean("tos_accepted", DEFAULT_TOS_ACCEPTED);
	}
}
