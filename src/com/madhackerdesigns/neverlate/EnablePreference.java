package com.madhackerdesigns.neverlate;

import android.content.Context;
import android.content.Intent;
import android.preference.CheckBoxPreference;
import android.util.AttributeSet;

public class EnablePreference extends CheckBoxPreference {

	Context mContext;
	
	public EnablePreference(Context context) {
		super(context);
		mContext = context;
	}

	public EnablePreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}
	
	public EnablePreference(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
	}

	/* (non-Javadoc)
	 * @see android.preference.CheckBoxPreference#onClick()
	 */
	@Override
	protected void onClick() {
		// Make sure the super gets its turn first
		super.onClick();
		
		// Then let's enable NeverLate
		mContext.sendBroadcast(new Intent(mContext, StartupReceiver.class));
	}

	

}
