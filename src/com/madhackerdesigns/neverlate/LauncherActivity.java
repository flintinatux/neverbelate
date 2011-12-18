/**
 * 
 */
package com.madhackerdesigns.neverlate;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
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

	private static final int DLG_COMING_SOON = 0;
	
	// Private fields
	private PreferenceHelper mPrefs;
	
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
		
		// Setup the Enable checkbox
		mPrefs = new PreferenceHelper(this);
		CheckBox enableBtn = (CheckBox) findViewById(R.id.btn_enable);
		enableBtn.setChecked(mPrefs.mNeverLateEnabled);
		enableBtn.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// Change the enabled state and enforce the change
				mPrefs.mNeverLateEnabled = isChecked;
				sendBroadcast(new Intent(LauncherActivity.this, StartupReceiver.class));
			}
			
		});
		
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
		
		// Create a coming soon dialog for features not yet implemented
		OnClickListener comingSoonListener = new OnClickListener() {

			public void onClick(View v) {
				showDialog(DLG_COMING_SOON);
			}
			
		};
		((Button) findViewById(R.id.btn_tips)).setOnClickListener(comingSoonListener);
		((Button) findViewById(R.id.btn_tutorial)).setOnClickListener(comingSoonListener);
		
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
	 * @see android.app.Activity#onCreateDialog(int)
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		switch (id) {
		case DLG_COMING_SOON:
			builder.setTitle(R.string.dlg_coming_soon_title)
				   .setMessage(R.string.dlg_coming_soon_msg)
			       .setCancelable(true)
			       .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			                dialog.cancel();
			           }
			       });
			break;
		}
		
		return builder.create();
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
