package com.madhackerdesigns.neverlate.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.madhackerdesigns.neverlate.util.Logger;

public class WakefulServiceReceiver extends BroadcastReceiver implements ServiceCommander {
	
	private static final String LOG_TAG = "NeverLateService";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		// Pass the bundle of extras on through to the service
		Bundle extras = intent.getExtras();
		int task = extras.getInt(EXTRA_SERVICE_COMMAND);
		Logger.d(LOG_TAG, "WakefulServiceReceiver sending task '" + task + "' to NeverLateService.");
		Intent serviceIntent = new Intent(context, NeverLateService.class);
//		serviceIntent.putExtra(EXTRA_SERVICE_COMMAND, task);
		serviceIntent.putExtras(extras);
		NeverLateService.sendWakefulWork(context, serviceIntent);
		
	}

}