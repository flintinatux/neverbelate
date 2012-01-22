package com.madhackerdesigns.neverlate.service;

import com.madhackerdesigns.neverlate.util.Logger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class WakefulServiceReceiver extends BroadcastReceiver implements ServiceCommander {
	
	private static final String LOG_TAG = "NeverLateService";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		// Pass the bundle of extras on through to the service
		Bundle extras = intent.getExtras();
		int task = extras.getInt(EXTRA_SERVICE_COMMAND);
		Logger.d(LOG_TAG, "WakefulServiceReceiver sending task '" + task + "' to NeverLateService.");
		Intent serviceIntent = new Intent(context, NeverLateService.class);
		serviceIntent.putExtras(extras);
		WakefulIntentService.sendWakefulWork(context, serviceIntent);
		
	}

}
