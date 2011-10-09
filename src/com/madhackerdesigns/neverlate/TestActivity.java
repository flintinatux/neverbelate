/**
 * 
 */
package com.madhackerdesigns.neverlate;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.commonsware.cwac.wakeful.WakefulIntentService;

/**
 * @author flintinatux
 *
 */
public class TestActivity extends Activity {

//	private static final int MENU_SETTINGS = Menu.FIRST;
	
	private AdHelper mAdHelper;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_activity);
		
		// Load up a new AdHelper
		mAdHelper = new AdHelper(getApplicationContext());
		
		// Grab the test button and add some action
		Button testButton = (Button) findViewById(R.id.test_button);
		testButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Tell NeverLateService to check for travel times
				Context context = getApplicationContext();
				Intent serviceIntent = new Intent(context, NeverLateService.class);
				serviceIntent.putExtra(NeverLateService.EXTRA_TASK, "CHECK_TRAVEL_TIMES");
				WakefulIntentService.sendWakefulWork(context, serviceIntent);
			}
			
		});
		
		// Setup the Settings button
		Button settingsButton = (Button) findViewById(R.id.settings_button);
		settingsButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Send user to the Settings menu
				Intent intent = new Intent(getApplicationContext(), NeverLateSettings.class);
	        	startActivity(intent);
			}
			
		});
		
		// Setup the Reset alert list button
		Button resetAlertListButton = (Button) findViewById(R.id.reset_alert_list_button);
		resetAlertListButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Delete all AlertsProvider entries
				getContentResolver().delete(AlertsContract.Alerts.CONTENT_URI, null, null);
			}
			
		});
		
		// Setup the Reset AdHelper button
		Button resetAdHelperButton = (Button) findViewById(R.id.reset_ad_helper_button);
		resetAdHelperButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Reset the AdHelper values
				mAdHelper.initFirstTimeUser();
			}
			
		});
		
		// Initialize the Pontiflex AdManager
//		IAdManager adManager = AdManagerFactory.createInstance(getApplication());
		
		
	}

//	/* (non-Javadoc)
//	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
//	 */
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		boolean result = super.onCreateOptionsMenu(menu);
//		menu.add(0, MENU_SETTINGS, 0, "Settings");
//		return result;
//	}
//	
//	/* (non-Javadoc)
//	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
//	 */
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//        case MENU_SETTINGS:
//        	Intent intent = new Intent(this, NeverLateSettings.class);
//        	startActivity(intent);
//            return true;
//        }
//		
//		return super.onOptionsItemSelected(item);
//	}

}
