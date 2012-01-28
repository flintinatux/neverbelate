package com.madhackerdesigns.neverbelate.settings;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.Preference;
import android.util.AttributeSet;

public class WebsitePreference extends Preference {

	private static final Uri WESBITE_URI = Uri.parse("http://www.madhackerdesigns.com/index.php");
	
	private Context mContext;
	
	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public WebsitePreference(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public WebsitePreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}

	/**
	 * @param context
	 */
	public WebsitePreference(Context context) {
		super(context);
		mContext = context; 
	}

	/* (non-Javadoc)
	 * @see android.preference.Preference#onClick()
	 */
	@Override
	protected void onClick() {
		// Launch the website
		Intent intent = new Intent(Intent.ACTION_VIEW, WESBITE_URI);
		intent.addCategory(Intent.CATEGORY_BROWSABLE);
		mContext.startActivity(intent);
	}

	
}
