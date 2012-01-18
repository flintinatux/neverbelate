/**
 * 
 */
package com.madhackerdesigns.neverlate;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author smccormack
 *
 */
public class AdHelper {

	// AdHelper SharedPreferences file and ad frequency
	private static final String AD_HELPER_VALUES = "ad_helper_values";
	private static final long AD_FREQUENCY = 4;		// show add every 4th use
	
	// Keys for AdHelper SharedPreference values
	private static final String CURRENT = "current";				// (long) current number of warnings dismissed
	private static final String AD_LAST = "ad_last";				// (long) last time that an ad was shown
	private static final String AD_NEXT = "ad_next";				// (long) next time to show an ad
	private static final String FIB_LAST = "fib_prev";				// (long) previous number in fibonacci sequence
	private static final String FIB_NEXT = "fib_next";				// (long) next number in fibonacci sequence
	private static final String REGISTER_LAST = "register_last";	// (long) last time that registration was requested
	private static final String REGISTER_NEXT = "register_next";	// (long) next time to ask the user to register
	private static final String IS_REGISTERED = "is_registered";	// (boolean) whether or not the user is registered
	private static final String REQUEST_NEXT = "request_next";		// (long) next version of request copy to use
	
	// Default AdHelper data values
	private static final long DEF_CURRENT = 0;					// 0 = no warnings have been dismissed yet
	private static final long DEF_AD_LAST = 0;					// 0 = no ads have been shown yet
	private static final long DEF_AD_NEXT = 2;					// 4 = show first ad on 4th use
	private static final long DEF_FIB_LAST = 2;					// 2 = last number in fibonacci sequence
	private static final long DEF_FIB_NEXT = 3;					// 3 = next number in fibonacci sequence
	private static final long DEF_REGISTER_LAST = 0;			// 0 = request registration on first use
	private static final long DEF_REGISTER_NEXT = 0;			// 0 = request registration on first use
	private static final boolean DEF_IS_REGISTERED = false;		// false = not registered at first
	private static final long DEF_REQUEST_NEXT = 0;				// 0 = use first reg. request copy text
		
	// Private member fields for AdHelper instance
	private SharedPreferences mSettings;
	private SharedPreferences.Editor mEditor;
	
	/**
	 * 
	 */
	public AdHelper(Context applicationContext) {
		super();	// TODO: Is this necessary?
		
		// Load the AdHelper values and editor 
		mSettings = applicationContext.getSharedPreferences(AD_HELPER_VALUES, Context.MODE_PRIVATE);
		mEditor = mSettings.edit();
		
		// Initialize preferences if this is the first time
		if ( ! mSettings.contains(CURRENT) ) {
			initFirstTimeUser();
		}
	}

	public String getNextRegRequest() {
		// May start with static set of request copy text, but would like to
		// be able to pull down copy from server based on what works or not.
		
		// TODO:  Continue here!
		return null;
	}
	
	public void initFirstTimeUser() {
		// Initialize the AdHelper SharedPreferences with default values
		SharedPreferences.Editor editor = mEditor;
		editor.putLong(CURRENT, DEF_CURRENT);
		editor.putLong(AD_LAST, DEF_AD_LAST);
		editor.putLong(AD_NEXT, DEF_AD_NEXT);
		editor.putLong(FIB_LAST, DEF_FIB_LAST);
		editor.putLong(FIB_NEXT, DEF_FIB_NEXT);
		editor.putLong(REGISTER_LAST, DEF_REGISTER_LAST);
		editor.putLong(REGISTER_NEXT, DEF_REGISTER_NEXT);
		editor.putBoolean(IS_REGISTERED, DEF_IS_REGISTERED);
		editor.putLong(REQUEST_NEXT, DEF_REQUEST_NEXT);
		editor.commit();
	}
	
	public boolean isRegistered() {
		// Return whether the user is registered
		return mSettings.getBoolean(IS_REGISTERED, DEF_IS_REGISTERED);
	}
	
	public boolean isTimeToRegister() {
		// Pull latest values
		SharedPreferences settings = mSettings;
		long current = settings.getLong(CURRENT, DEF_CURRENT);
		long regLast = settings.getLong(REGISTER_LAST, DEF_REGISTER_LAST);
		long regNext = settings.getLong(REGISTER_NEXT, DEF_REGISTER_NEXT);
		
		// Return true if current warning is next time to request registration
		return (current >= regNext && (regNext == 0 || current != regLast));
	}
	
	public boolean isTimeToShowAd() {
		// Uncomment to quit showing ads we don't need
//		return false;
		
		// Pull latest values
		SharedPreferences settings = mSettings;
		long current = settings.getLong(CURRENT, DEF_CURRENT);
		long adLast = settings.getLong(AD_LAST, DEF_AD_LAST);
		long adNext = settings.getLong(AD_NEXT, DEF_AD_NEXT);
		
		// Return true if current warning is next time to show add
		return (current >= adNext && (adNext == 0 || current != adLast));
	}
	
	public void setAdShown(boolean adShown) {
		// Pull latest values
		long current = mSettings.getLong(CURRENT, DEF_CURRENT);
		long adNext = mSettings.getLong(AD_NEXT, DEF_AD_NEXT);
		
		// Set the current warning as the last time the ad was shown,
		// and bump up the adNext value by the ad frequency.
		SharedPreferences.Editor editor = mEditor;
		editor.putLong(AD_LAST, current);
		editor.putLong(AD_NEXT, adNext + AD_FREQUENCY);
		editor.commit();
	}
	
	public void setRegistered(boolean registered) {
		// Set if user is registered
		if (registered) {
			mEditor.putBoolean(IS_REGISTERED, registered);
			mEditor.commit();
		}
	}
	
	public void setRegistrationRequested(boolean requested) {
		// Registration was requested of the user (user either registered or skipped)
		SharedPreferences settings = mSettings;
		long current = settings.getLong(CURRENT, DEF_CURRENT);
		long fibLast = settings.getLong(FIB_LAST, DEF_FIB_LAST);
		long fibNext = settings.getLong(FIB_NEXT, DEF_FIB_NEXT);
		long regNext = settings.getLong(REGISTER_NEXT, DEF_REGISTER_NEXT);
		
		// Add the next fibonacci number to the next registration request
		SharedPreferences.Editor editor = mEditor;
		editor.putLong(REGISTER_NEXT, regNext + fibNext);
		editor.putLong(REGISTER_LAST, current);
		editor.putLong(FIB_LAST, DEF_FIB_NEXT);
		editor.putLong(FIB_NEXT, fibLast + fibNext);
		editor.commit();
	}
	
	public void setWarningDismissed(boolean dismissed) {
		// Dismiss warning (add one to current value)
		if (dismissed) {
			long current = mSettings.getLong(CURRENT, DEF_CURRENT);
			mEditor.putLong(CURRENT, current + 1);
			mEditor.commit();
		}
	}
	
}
