package com.madhackerdesigns.neverbelate.ui;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.madhackerdesigns.neverbelate.util.Logger;

public class CreateNewEvent extends Activity {
	
	private static final String LOG_TAG = "CreateNewEvent";

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Get the intent data
		Uri data = getIntent().getData();
		Logger.v(LOG_TAG, "Data Uri is " + data.toString());
		
		// Determine the scheme of the Uri
		String scheme = data.getScheme();
		String destination;
		if (scheme.equals("geo")) {
			// geo:0,0?q=Cartersville%2C GA  30120-8232
			destination = data.getQuery().substring(2);
		} else if (scheme.equals("content")) {
			// content://com.android.contacts/data/2316
			ContentResolver cr = getContentResolver();
			Cursor cursor = cr.query(data, null, null, null, null);
			String log = "Cursor columns are " + TextUtils.join(", ", cursor.getColumnNames());
			Logger.v(LOG_TAG, log);
		}
		
		// Finish for now
		finish();
	}

}
