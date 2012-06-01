/**
 * 
 */
package com.madhackerdesigns.neverbelate.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.madhackerdesigns.neverbelate.R;
import com.madhackerdesigns.neverbelate.provider.AlertsContract;
import com.madhackerdesigns.neverbelate.reg.Registration;
import com.madhackerdesigns.neverbelate.reg.RegistrationForm;
import com.madhackerdesigns.neverbelate.service.NeverBeLateService;
import com.madhackerdesigns.neverbelate.service.ServiceCommander;
import com.madhackerdesigns.neverbelate.util.AdHelper;

/**
 * @author flintinatux
 *
 */
public class TestActivity extends Activity implements ServiceCommander {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_activity);
		
		new AdHelper(getApplicationContext());
		
		// Grab the test button and add some action
		Button testButton = (Button) findViewById(R.id.test_button);
		testButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// Tell NeverBeLateService to check for travel times
				Context context = getApplicationContext();
				Intent serviceIntent = new Intent(context, NeverBeLateService.class);
				serviceIntent.putExtra(EXTRA_SERVICE_COMMAND, CHECK_TRAVEL_TIMES);
				startService(serviceIntent);
			}
			
		});
		
		// Setup the Reset alert list button
		Button resetAlertListButton = (Button) findViewById(R.id.reset_alert_list_button);
		resetAlertListButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// Delete all AlertsProvider entries
				getContentResolver().delete(AlertsContract.Alerts.CONTENT_URI, null, null);
			}
			
		});
		
		// Setup the Clear registration button
		Button btnMultioffer = (Button) findViewById(R.id.btn_clear_reg);
		btnMultioffer.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// Clear the stored registration values
				(new Registration(getApplicationContext())).clearStoredRegistration();
			}
			
		});
		
		// Setup the Reset AdHelper button
		Button btnRegForm = (Button) findViewById(R.id.btn_reg_form);
		btnRegForm.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// Reset the AdHelper values
				startActivity(new Intent(getApplicationContext(), RegistrationForm.class));
			}
			
		});
		
		// Setup the Rate dialog test button
		Button btnRateDlg = (Button) findViewById(R.id.btn_rate_dlg);
		btnRateDlg.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// Set the alert count to 7 before testing warning
				SharedPreferences alertStats = getSharedPreferences("alert_stats", Activity.MODE_PRIVATE);
				alertStats.edit().putLong("alert_stats.alert_count", 7).commit();
				alertStats.edit().putBoolean("alert_stats.rated_already", false).commit();
			}
			
		});
		
	}

}
