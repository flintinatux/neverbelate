package com.madhackerdesigns.neverlate;

import com.commonsware.cwac.wakeful.WakefulIntentService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TravelTimeReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		// TODO: implement wakelock policy
		
		// send startService to check for travel times
		Intent serviceIntent = new Intent(context, NeverLateService.class);
		serviceIntent.putExtra("task", "CHECK_TRAVEL_TIMES");
//		serviceIntent.setAction("com.madhackerdesigns.neverlate.NeverLateService");
//		context.startService(serviceIntent);
		WakefulIntentService.sendWakefulWork(context, serviceIntent);
		
		// TODO: release wakelock based on policy
		
	}

}
