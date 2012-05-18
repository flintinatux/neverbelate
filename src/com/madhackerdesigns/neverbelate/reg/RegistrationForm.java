/*
* Copyright (C) 2007 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.madhackerdesigns.neverbelate.reg;

import java.util.List;
import java.util.regex.Pattern;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.madhackerdesigns.neverbelate.R;
import com.madhackerdesigns.neverbelate.ui.LauncherActivity;
import com.madhackerdesigns.neverbelate.util.Logger;

/**
 * @author flintinatux
 *
 */
public class RegistrationForm extends Activity {

	// Member fields for the Registration Form
	private Registration mRegistration;
	
	// Unique dialog id's
	private static final int DIALOG_DECLINE = 0;
    private static final int DIALOG_INCOMPLETE = 1;
	private static final int DIALOG_THANKS = 2;
	
	// Email regex pattern (borrowed from Android API 8+)
	public static final Pattern EMAIL_PATTERN
	        = Pattern.compile(
	            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
	            "\\@" +
	            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
	            "(" +
	            "\\." +
	            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
	            ")+"
	        );
    
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_form);
		setTitle(R.string.reg_form_title);
		
		// Initialize the Registration data
		mRegistration = new Registration(getApplicationContext());
		
		// Pre-pop user's email address
		String userEmail = "";
		Account[] accounts = AccountManager.get(this).getAccounts();
		for (Account account : accounts) {
		    if (EMAIL_PATTERN.matcher(account.name).matches()) {
		        // Just go ahead and use the first match
		    	userEmail = account.name;
		    	break;
		    }
		}
		((TextView) findViewById(R.id.email)).setText(userEmail);
		
		// Set the behavior of the country "spinner"
		Button btnCountry = (Button) findViewById(R.id.btn_country);
		btnCountry.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO: Show country select dialog and disable zipcode if needed
			}
			
		});
		
		// TODO: Reverse geocode the user's location
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		List<String> providers = lm.getAllProviders();
		Location loc = null;
		for (String provider : providers) {
			// Just roll through location providers until we get one
			loc = lm.getLastKnownLocation(provider);
			if (loc != null) { break; }
		}
		try {
			GeoCodeResult result = (new GeoCoder()).reverseGeoCode(loc.getLatitude(), loc.getLongitude());
			// TODO: Set country code if available
			// TODO: Disable zip code field if zip code not used in that country
			((TextView) findViewById(R.id.zip_code)).setText(result.getZipCode());
		} catch (Exception e) {
			// Fail quietly
			e.printStackTrace();
			Logger.d("Prepop of country and zip codes failed.");
		}
		
		// Set the OnClick behavior of the decline text
		TextView declineText = (TextView) findViewById(R.id.decline_text);
		declineText.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				showDialog(DIALOG_DECLINE);
			}
			
		});
		
		// Set the OnClick behavior of the Register button
		Button regButton = (Button) findViewById(R.id.btn_register);
		regButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				registerUser();
			}
			
		});
		
	} 

	private void registerUser() {
		// Pull registration data from form
		String firstName = (String) ((TextView) findViewById(R.id.first_name)).getText().toString();
		String lastName = (String) ((TextView) findViewById(R.id.last_name)).getText().toString();
		String email = (String) ((TextView) findViewById(R.id.email)).getText().toString();
		// TODO: String countryCode
		String zipCode = (String) ((TextView) findViewById(R.id.zip_code)).getText().toString();
		
		// Remind user to complete form if he didn't.
		if ( 	isEmpty(firstName) || 
				isEmpty(lastName) ||
				isEmpty(email) ||
				isEmpty(zipCode) ) {		// TODO: isEmpty(countryCode)
			
			// Show reminder dialog and return.
			showDialog(DIALOG_INCOMPLETE);
			return;
			
		}
		
		// If registration validates, permanently store the data.
		Registration reg = mRegistration;
		reg.setFirstName(firstName);
		reg.setLastName(lastName);
		reg.setEmail(email);
		reg.setZipCode(zipCode);
		// TODO: Add reg.setCountryCode(country)
		
		// TODO: Insert reg data into Pontiflex 
		
		// Then say thanks and pass them on to the main launcher screen
		showDialog(DIALOG_THANKS);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateDialog(int)
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch (id) {
	    case DIALOG_DECLINE:
	    	// Ask user if they really want to decline registration.
	    	builder.setTitle(R.string.dialog_decline_title)
	    		.setMessage(R.string.dialog_decline_message)
	    		.setPositiveButton(R.string.dialog_decline_btn_yes, new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						RegistrationForm.this.finish();
					}
					
				})
	    		.setNegativeButton(R.string.dialog_decline_btn_no, new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
					
				});
	    	return builder.create();
	    case DIALOG_INCOMPLETE:
	    	// Remind user to complete the form to register.
	    	builder.setTitle(R.string.dialog_incomplete_title)
	    		.setMessage(R.string.dialog_incomplete_message)
	    		.setNeutralButton(R.string.dialog_incomplete_btn_ok, new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
					
				});
	    	return builder.create();
	    case DIALOG_THANKS:
	    	// Thank the user for registering and pass them on to the main activity
	    	builder.setTitle(R.string.dialog_thanks_title)
	    		.setMessage(R.string.dialog_thanks_message)
	    		.setNeutralButton(R.string.dialog_thanks_btn_ok, new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						Intent i = new Intent(getApplicationContext(), LauncherActivity.class);
						startActivity(i);
						RegistrationForm.this.finish();
					}
					
				});
	    	return builder.create();
	    }
	    return null;
	}
	
	private boolean isEmpty (String s) {
		return (s == null || s.length() == 0);
	}
	
	

}
