package com.madhackerdesigns.neverbelate.reg;

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
	
	public void clearStoredRegistration() {
		this.setCountryCode("");
		this.setEmail("");
		this.setFirstName("");
		this.setLastName("");
		this.setZipCode("");
		this.setRegistered(false);
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
	protected boolean setCountryCode(String countryCode) {
		return mSettings.edit().putString(COUNTRY_CODE, countryCode).commit();
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
	protected boolean setEmail(String email) {
		return mSettings.edit().putString(EMAIL, email).commit();
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
	protected boolean setFirstName(String firstName) {
		return mSettings.edit().putString(FIRST_NAME, firstName).commit();
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
	protected boolean setLastName(String lastName) {
		return mSettings.edit().putString(LAST_NAME, lastName).commit();
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
	protected boolean setRegistered(boolean registered) {
		return mSettings.edit().putBoolean(REGISTERED, registered).commit();
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
	protected boolean setZipCode(String zipCode) {
		return mSettings.edit().putString(ZIP_CODE, zipCode).commit();
	}
	
}
