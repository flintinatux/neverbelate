/**
 * 
 */
package com.madhackerdesigns.neverlate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewSwitcher;

import com.pontiflex.mobile.webview.sdk.AdManagerFactory;
import com.pontiflex.mobile.webview.sdk.IAdManager;

/**
 * @author smccormack
 *
 */
public class LauncherActivity extends Activity {

	// Result codes
	private static final int RESULT_TOS = 0;
	
	// Registration settings
	private static final int LAUNCH_INTERVAL = 3;
	
	// Private fields
	private AdHelper mAdHelper;
	private PreferenceHelper mPrefs;
	private ViewSwitcher mSwitcher;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		
		// Show TOS and EULA for acceptance
		Eula.show(this);
		
		// TODO: If first load, download the registration letter copy.
		// (For now, just get a static copy from the strings resources.) 
		
		// TODO: Present registration letter if first use.
		// (Letter dialog will start registration activity.)
				
		// Show registration if first time
		IAdManager adManager = AdManagerFactory.createInstance(getApplication());
		adManager.setRegistrationInterval(LAUNCH_INTERVAL);
		adManager.setRegistrationMode(IAdManager.RegistrationMode.RegistrationAfterIntervalInLaunches);
		
		// Set content view to launcher layout
		setContentView(R.layout.launcher);
		
		// Load the app logo at the top.
		View logo = (View) findViewById(R.id.logo);
		
		// Load up the view switcher between the loading view and the button view.
		ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.launcher_switcher);
		switcher.addView(View.inflate(this, R.layout.launcher_loading, null));
		switcher.addView(View.inflate(this, R.layout.launcher_buttons, null));
		
		// TODO: Switch view to buttons
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode) {
		case RESULT_TOS:
			if (resultCode == 1) {
				// TODO: Set tos_accepted to true
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		
	}
	
	

}
