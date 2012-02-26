package com.madhackerdesigns.neverbelate.settings;

import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.preference.Preference;
import android.util.AttributeSet;

import com.madhackerdesigns.neverbelate.R;

public class FeedbackPreference extends Preference {

	private Context mContext;
	
	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public FeedbackPreference(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public FeedbackPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}

	/**
	 * @param context
	 */
	public FeedbackPreference(Context context) {
		super(context);
		mContext = context; 
	}

	/* (non-Javadoc)
	 * @see android.preference.Preference#onClick()
	 */
	@Override
	protected void onClick() {
		// Generate and send an intent with a feedback message (email)
		Resources res = mContext.getResources();
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("text/plain");
		i.putExtra(Intent.EXTRA_EMAIL, new String[] { res.getString(R.string.feedback_address) });
		String subject = res.getString(R.string.feedback_subject) + (new Date().getTime()) + 
				"(" + res.getString(R.string.version_name) + ")";
		i.putExtra(Intent.EXTRA_SUBJECT, subject);
		mContext.startActivity(Intent.createChooser(i, res.getString(R.string.feedback_menu_title)));
	}

	
}
