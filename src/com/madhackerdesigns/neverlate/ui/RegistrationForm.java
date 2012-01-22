/**
 * 
 */
package com.madhackerdesigns.neverlate.ui;

import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;

import com.madhackerdesigns.neverlate.R;

/**
 * @author flintinatux
 *
 */
public class RegistrationForm extends Activity {

	// Member fields for the Registration Form
	private Button mBirthdateButton;
	private boolean mBirthdateSet = false;
	private int mYear = 1980;
    private int mMonth = 1;
    private int mDay = 1;
	private OnDateSetListener mDateSetListener;
	private Registration mRegistration;
	private String mGender;
	
	// Unique dialog id's
	private static final int DIALOG_DATE = 0;
    private static final int DIALOG_DECLINE = 1;
    private static final int DIALOG_INCOMPLETE = 2;
	private static final int DIALOG_THANKS = 3;
    
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
		
		// Add birthdate selection dialog
		mBirthdateButton = (Button) findViewById(R.id.btn_birthdate);
		mBirthdateButton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				showDialog(DIALOG_DATE);
			}
			
		});
		
		// Initialize the OnDateSetListener
		mDateSetListener = new DatePickerDialog.OnDateSetListener() {

			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				mYear = year;
                mMonth = monthOfYear;
                mDay = dayOfMonth;
                mBirthdateSet = true;
                updateBirthdateButton();
			}
			
		};
		
		// Set the OnClick behavior of the gender radio buttons
		OnClickListener genderListener = new OnClickListener() {

			public void onClick(View v) {
				mGender = (String) ((RadioButton) v).getText();
			}
			
		};
		RadioButton btnRadioMale = (RadioButton) findViewById(R.id.btn_radio_male);
		RadioButton btnRadioFemale = (RadioButton) findViewById(R.id.btn_radio_female);
		btnRadioMale.setOnClickListener(genderListener);
		btnRadioFemale.setOnClickListener(genderListener);
		
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
		String betaCode = (String) ((TextView) findViewById(R.id.beta_code)).getText();
		String firstName = (String) ((TextView) findViewById(R.id.first_name)).getText();
		String lastName = (String) ((TextView) findViewById(R.id.last_name)).getText();
		long birthdate = (new GregorianCalendar(mYear, mMonth, mDay)).getTimeInMillis();
		int zipCode = 0;
		try {
			zipCode = Integer.parseInt((String) ((TextView) findViewById(R.id.zip_code)).getText());
		} catch (NumberFormatException e) {
			// TODO: Will this catch an empty string in the zipCode field?
		}
		
		// Decide on gender.
		Resources res = getResources();
		String male = res.getString(R.string.male);
		String female = res.getString(R.string.female);
		int gender;
		if (mGender.equals(male)) {
			gender = Registration.GENDER_MALE;
		} else if (mGender.equals(female)) {
			gender = Registration.GENDER_FEMALE;
		} else {
			gender = Registration.GENDER_UNKNOWN;
		}
		
		// Remind user to complete form if he didn't.
		if ( 	betaCode.equals("") || 		// TODO: Test that this corresponds to an incomplete form.
				firstName.equals("") || 
				lastName.equals("") ||
				! mBirthdateSet || 
				zipCode == 0 ||
				gender == Registration.GENDER_UNKNOWN ) {
			
			// Show reminder dialog and return.
			showDialog(DIALOG_INCOMPLETE);
			return;
			
		}
		
		// TODO: Attempt upload to registration server.
		
		// If registration succeeds, permanently store the data.
		Registration reg = mRegistration;
		reg.setBetaCode(betaCode);
		reg.setFirstName(firstName);
		reg.setLastName(lastName);
		reg.setBirthdate(birthdate);
		reg.setZipCode(zipCode);
		reg.setGender(gender);
		reg.commit();
		
		// Then say thanks and pass them on to the main launcher screen
		showDialog(DIALOG_THANKS);
	}

	private void updateBirthdateButton() {
		String month = DateUtils.getMonthString(mMonth, DateUtils.LENGTH_MEDIUM);
		mBirthdateButton.setText(month + " " + mDay + ", " + mYear);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateDialog(int)
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch (id) {
	    case DIALOG_DATE:
	        return new DatePickerDialog(this,
	                    mDateSetListener,
	                    mYear, mMonth, mDay);
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
	
	

}
