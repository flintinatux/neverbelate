package com.madhackerdesigns.neverbelate.ui;

import android.content.Context;
import android.content.SharedPreferences;

public class Registration {

	// SharedPreference keys for registration data
	private static final String COUNTRY_CODE = "reg.country_code";
	private static final String EMAIL = "reg.email";
	private static final String FIRST_NAME = "reg.first_name";
	private static final String LAST_NAME = "reg.last_name";
	private static final String REGISTERED = "reg.registered";
	private static final String ZIP_CODE = "reg.zip_code";
	
	// SharedPreferences stuff
	private static final String REGISTRATION_DATA = "registration_data";
	private SharedPreferences mSettings;
	
	/**
	 * @param applicationContext 
	 * 
	 */
	public Registration(Context applicationContext) {
		// Load the Registration values and editor 
		mSettings = applicationContext.getSharedPreferences(REGISTRATION_DATA, Context.MODE_PRIVATE);
	}

	/**
	 * @return the 2-letter country code
	 */
	protected String getCountryCode() {
		return mSettings.getString(COUNTRY_CODE, "");
	}
	
	/**
	 * @param countryCode the 2-letter country code to set
	 */
	protected void setCountryCode(String countryCode) {
		mSettings.edit().putString(COUNTRY_CODE, countryCode);
	}

	/**
	 * @return the email
	 */
	protected String getEmail() {
		return mSettings.getString(EMAIL, "");
	}
	
	/**
	 * @param email the email to set
	 */
	protected void setEmail(String email) {
		mSettings.edit().putString(EMAIL, email);
	}

	/**
	 * @return the firstName
	 */
	protected String getFirstName() {
		return mSettings.getString(FIRST_NAME, "");
	}

	/**
	 * @param firstName the firstName to set
	 */
	protected void setFirstName(String firstName) {
		mSettings.edit().putString(FIRST_NAME, firstName);
	}

	/**
	 * @return the lastName
	 */
	protected String getLastName() {
		return mSettings.getString(LAST_NAME, "");
	}

	/**
	 * @param lastName the lastName to set
	 */
	protected void setLastName(String lastName) {
		mSettings.edit().putString(LAST_NAME, lastName);
	}

	/**
	 * @return true if registration is completed
	 */
	protected boolean isRegistered() {
		return mSettings.getBoolean(REGISTERED, false);
	}

	/**
	 * @param registered true if registration is completed
	 */
	protected void setRegistered(boolean registered) {
		mSettings.edit().putBoolean(REGISTERED, registered);
	}

	/**
	 * @return the zipCode
	 */
	protected String getZipCode() {
		return mSettings.getString(ZIP_CODE, "");
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	protected void setZipCode(String zipCode) {
		mSettings.edit().putString(ZIP_CODE, zipCode);
	}
	
}
