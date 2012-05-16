/**
 * 
 */
package com.madhackerdesigns.neverbelate.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.madhackerdesigns.neverbelate.R;

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
		
		// Set the behavior of the country "spinner"
		Button btnCountry = (Button) findViewById(R.id.btn_country);
		btnCountry.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO: Show country select dialog and disable zipcode if needed
			}
			
		});
		
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
		String firstName = (String) ((TextView) findViewById(R.id.first_name)).getText();
		String lastName = (String) ((TextView) findViewById(R.id.last_name)).getText();
		String email = (String) ((TextView) findViewById(R.id.email)).getText();
		// TODO: String countryCode
		String zipCode = (String) ((TextView) findViewById(R.id.zip_code)).getText();
		
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
