package com.madhackerdesigns.neverbelate.settings;

import android.app.Activity;
import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;

import com.madhackerdesigns.neverbelate.Eula;

public class EulaPreference extends Preference {

	private Context mContext;
	
	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public EulaPreference(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public EulaPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}

	/**
	 * @param context
	 */
	public EulaPreference(Context context) {
		super(context);
		mContext = context; 
	}

	/* (non-Javadoc)
	 * @see android.preference.Preference#onClick()
	 */
	@Override
	protected void onClick() {
		// Show the EULA dialog
		Eula.display((Activity) mContext);
	}

	
}
