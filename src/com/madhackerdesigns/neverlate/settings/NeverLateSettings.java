/**
 * 
 */
package com.madhackerdesigns.neverlate.settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.madhackerdesigns.neverlate.R;

/**
 * @author flintinatux
 *
 */
public class NeverLateSettings extends PreferenceActivity {
	
	/* (non-Javadoc)
	 * @see android.preference.PreferenceActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// pull preferences from prefs file
		addPreferencesFromResource(R.xml.settings);
		setTitle(R.string.app_title);
		
	}

}
