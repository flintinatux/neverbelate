/**
 * 
 */
package com.madhackerdesigns.neverlate;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
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
	private Resources mRes;
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
				
		// Show registration if time
		IAdManager adManager = AdManagerFactory.createInstance(getApplication());
		adManager.setRegistrationInterval(LAUNCH_INTERVAL);
		adManager.setRegistrationMode(IAdManager.RegistrationMode.RegistrationAfterIntervalInLaunches);
		
		// Set content view to launcher layout and load application resources
		setContentView(R.layout.launcher);
		mRes = getResources();
		
		// Load the app logo at the top.
		ImageView logo = (ImageView) findViewById(R.id.logo);
		// TODO: Create new large format logo for launcher
		logo.setImageResource(R.drawable.ic_launcher_rabbit3);
		
		// Load the config button
		Button configBtn = (Button) findViewById(R.id.btn_configure);
		configBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// Send user to the Settings menu
				Intent intent = new Intent(getApplicationContext(), NeverLateSettings.class);
	        	startActivity(intent);
			}
			
		});
		
		// Load the share button
		Button shareBtn = (Button) findViewById(R.id.btn_share);
		shareBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				shareNeverLate();
			}
			
		});
		
		// TODO: Create a coming soon dialog for features not yet implemented
	}

	protected void shareNeverLate() {
		// Send out a share intent for the this app's URI in the market
		Resources res = mRes;
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("text/plain");
		i.putExtra(Intent.EXTRA_SUBJECT, res.getString(R.string.share_subject));
		i.putExtra(Intent.EXTRA_TEXT, res.getString(R.string.share_message));
		startActivity(Intent.createChooser(i, res.getString(R.string.share_menu_title)));
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
