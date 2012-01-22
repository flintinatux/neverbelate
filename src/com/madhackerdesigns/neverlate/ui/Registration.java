package com.madhackerdesigns.neverlate.ui;

import android.content.Context;
import android.content.SharedPreferences;

public class Registration {

	// Private member fields for Registration data
	private String betaCode;
	private long birthdate;
	private String email; 		// TODO: Create a way for beta version to download email per beta code.
	private int gender;
	private String firstName;
	private String lastName;
	private String promoCode;
	private boolean registered;
	private int zipCode;
	
	// SharedPreference keys for registration data
	private static final String BETA_CODE = "beta_code";
	private static final String BIRTHDATE = "birthdate";
	private static final String GENDER = "gender";
	private static final String FIRST_NAME = "first_name";
	private static final String LAST_NAME = "last_name";
	private static final String PROMO_CODE = "promo_code";
	private static final String REGISTERED = "registered";
	private static final String ZIP_CODE = "zip_code";
	
	// Static constants for gender of registered user
	protected static final int GENDER_MALE = 0;
	protected static final int GENDER_FEMALE = 1;
	protected static final int GENDER_UNKNOWN = 2;
	
	// SharedPreferences stuff
	private static final String REGISTRATION_DATA = "registration_data";
	private SharedPreferences mSettings;
	private SharedPreferences.Editor mEditor;
	
	
	/**
	 * @param applicationContext 
	 * 
	 */
	public Registration(Context applicationContext) {
		super();	// TODO: Is this necessary?
		
		// Load the Registration values and editor 
		mSettings = applicationContext.getSharedPreferences(REGISTRATION_DATA, Context.MODE_PRIVATE);
		mEditor = mSettings.edit();
		
		// Load Registration data if registered
		if ( mSettings.contains(REGISTERED) ) {
			registered = mSettings.getBoolean(REGISTERED, false);
			if (registered) {
				loadRegistration();
			}
		}
	}

	private void loadRegistration() {
		betaCode = mSettings.getString(BETA_CODE, null);
		birthdate = mSettings.getLong(BIRTHDATE, 0);
		gender = mSettings.getInt(GENDER, GENDER_UNKNOWN);
		firstName = mSettings.getString(FIRST_NAME, null);
		lastName = mSettings.getString(LAST_NAME, null);
		promoCode = mSettings.getString(PROMO_CODE, null);
		zipCode = mSettings.getInt(ZIP_CODE, 0);
	}
	
	protected void commit() {
		mEditor.putString(BETA_CODE, betaCode);
		mEditor.putLong(BIRTHDATE, birthdate);
		mEditor.putInt(GENDER, gender);
		mEditor.putString(FIRST_NAME, firstName);
		mEditor.putString(LAST_NAME, lastName);
		mEditor.putString(PROMO_CODE, promoCode);
		mEditor.putBoolean(REGISTERED, registered);
		mEditor.putInt(ZIP_CODE, zipCode);
		mEditor.commit();
	}

	/**
	 * @return the betaCode
	 */
	protected String getBetaCode() {
		return betaCode;
	}

	/**
	 * @param betaCode the betaCode to set
	 */
	protected void setBetaCode(String betaCode) {
		this.betaCode = betaCode;
	}

	/**
	 * @return the birthdate
	 */
	protected long getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate the birthdate to set
	 */
	protected void setBirthdate(long birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the gender
	 */
	protected int getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	protected void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * @return the firstName
	 */
	protected String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	protected void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	protected String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	protected void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the promoCode
	 */
	protected String getPromoCode() {
		return promoCode;
	}

	/**
	 * @param promoCode the promoCode to set
	 */
	protected void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	/**
	 * @return the zipCode
	 */
	protected int getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	protected void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
}
