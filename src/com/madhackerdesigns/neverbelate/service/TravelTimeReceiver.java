package com.madhackerdesigns.neverbelate.service;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.madhackerdesigns.neverbelate.util.Logger;

public class TravelTimeReceiver extends BroadcastReceiver implements ServiceCommander {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		// send startService to check for travel times
		Logger.d("Received alarm to check travel times");
		Intent serviceIntent = new Intent(context, NeverBeLateService.class);
		serviceIntent.putExtra(EXTRA_SERVICE_COMMAND, CHECK_TRAVEL_TIMES);
		NeverBeLateService.sendWakefulWork(context, serviceIntent);
		
	}

}
