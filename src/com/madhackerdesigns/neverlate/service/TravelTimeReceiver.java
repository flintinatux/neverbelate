package com.madhackerdesigns.neverlate.service;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TravelTimeReceiver extends BroadcastReceiver implements ServiceCommander {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		// send startService to check for travel times
		Intent serviceIntent = new Intent(context, NeverLateService.class);
		serviceIntent.putExtra(EXTRA_SERVICE_COMMAND, CHECK_TRAVEL_TIMES);
		WakefulIntentService.sendWakefulWork(context, serviceIntent);
		
	}

}
