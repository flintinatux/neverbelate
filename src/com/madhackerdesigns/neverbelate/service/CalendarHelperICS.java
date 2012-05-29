package com.madhackerdesigns.neverbelate.service;

import android.net.Uri;
import android.provider.CalendarContract;

public class CalendarHelperICS extends CalendarHelper {

	public static final String[] INSTANCES_PROJECTION = {
		CalendarContract.Instances.EVENT_ID,			// 0
		CalendarContract.Events.TITLE,					// 1
		CalendarContract.Events.ALL_DAY,				// 2
		CalendarContract.Instances.BEGIN,				// 3
		CalendarContract.Instances.END,					// 4
		CalendarContract.Events.EVENT_LOCATION,			// 5
		CalendarContract.Events.DESCRIPTION,			// 6
		CalendarContract.Calendars.CALENDAR_COLOR		// 7
	};
	
	
	@Override
	public String getEventLocationColumn() {
		return CalendarContract.Events.EVENT_LOCATION;
	}

	@Override
	public String getHasAlarmColumn() {
		return CalendarContract.Events.HAS_ALARM;
	}

	@Override
	public Uri getInstancesUri() {
		return CalendarContract.Instances.CONTENT_URI;
	}

	@Override
	public String[] getInstancesProjection() {
		return INSTANCES_PROJECTION;
	}

}
