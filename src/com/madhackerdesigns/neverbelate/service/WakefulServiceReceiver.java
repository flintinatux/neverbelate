package com.madhackerdesigns.neverbelate.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.madhackerdesigns.neverbelate.util.Logger;

public class WakefulServiceReceiver extends BroadcastReceiver implements ServiceCommander {
	
	private static final String LOG_TAG = "NeverBeLateService";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		// Pass the bundle of extras on through to the service
		Bundle extras = intent.getExtras();
		int task = extras.getInt(EXTRA_SERVICE_COMMAND);
		Logger.d(LOG_TAG, "WakefulServiceReceiver sending task '" + task + "' to NeverBeLateService.");
		Intent serviceIntent = new Intent(context, NeverBeLateService.class);
//		serviceIntent.putExtra(EXTRA_SERVICE_COMMAND, task);
		serviceIntent.putExtras(extras);
		NeverBeLateService.sendWakefulWork(context, serviceIntent);
		
	}

}
