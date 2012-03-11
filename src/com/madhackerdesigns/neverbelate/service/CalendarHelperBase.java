package com.madhackerdesigns.neverbelate.service;

import android.net.Uri;
import android.os.Build;

public class CalendarHelperBase extends CalendarHelper {

	// android.provider.Calendar.Calendars
//		public static final String CALENDAR_DEFAULT_SORT_ORDER = "displayName";   // variance: DEFAULT_SORT_ORDER
//		public static final String DISPLAY_NAME = "displayName";
//		public static final String HIDDEN = "hidden";
//		public static final String LOCATION = "location";
//		public static final String CALENDAR_NAME = "name";   // variance: NAME
//		public static final String URL = "url";
		
		// android.provider.Calendar.CalendarsColumns
//		public static final String ACCESS_LEVEL = "access_level";
		public static final String COLOR = "color";
//		public static final int CONTRIBUTOR_ACCESS = 500;
//		public static final int EDITOR_ACCESS = 600;
//		public static final int FREEBUSY_ACCESS = 100;
//		public static final int NO_ACCESS = 0;
//		public static final int OVERRIDE_ACCESS = 400;
//		public static final int OWNER_ACCESS = 700;
//		public static final int READ_ACCESS = 200;
//		public static final int RESPOND_ACCESS = 300;
//		public static final int ROOT_ACCESS = 800;
//		public static final String SELECTED = "selected";
//		public static final String SYNC_EVENTS = "sync_events";
//		public static final String TIMEZONE = "timezone";


		// android.provider.Calendar.EventsColumns
		public static final String ALL_DAY = "allDay";
//		public static final String CALENDAR_ID = "calendar_id";
//		public static final String COMMENTS_URI = "commentsUri";
		public static final String DESCRIPTION = "description";
//		public static final String DTEND = "dtend";
//		public static final String DTSTART = "dtstart";
//		public static final String DURATION = "duration";
		public static final String EVENT_LOCATION = "eventLocation";
//		public static final String EVENT_TIMEZONE = "eventTimezone";
//		public static final String EXDATE = "exdate";
//		public static final String EXRULE = "exrule";
//		public static final String HAS_ALARM = "hasAlarm";
//		public static final String HAS_EXTENDED_PROPERTIES = "hasExtendedProperties";
//		public static final String HTML_URI = "htmlUri";
//		public static final String LAST_DATE = "lastDate";
//		public static final String ORIGINAL_ALL_DAY = "originalAllDay";
//		public static final String ORIGINAL_EVENT = "originalEvent";
//		public static final String ORIGINAL_INSTANCE_TIME = "originalInstanceTime";
//		public static final String RDATE = "rdate";
//		public static final String RRULE = "rrule";
//		public static final String SELF_ATTENDEE_STATUS = "selfAttendeeStatus";
//		public static final String STATUS = "eventStatus";
//		public static final int STATUS_CANCELED = 2;
//		public static final int STATUS_CONFIRMED = 1;
//		public static final int STATUS_TENTATIVE = 0;
		public static final String TITLE = "title";
//		public static final String TRANSPARENCY = "transparency";
//		public static final int TRANSPARENCY_OPAQUE = 0;
//		public static final int TRANSPARENCY_TRANSPARENT = 1;
//		public static final String VISIBILITY = "visibility";
//		public static final int VISIBILITY_CONFIDENTIAL = 1;
//		public static final int VISIBILITY_DEFAULT = 0;
//		public static final int VISIBILITY_PRIVATE = 2;
//		public static final int VISIBILITY_PUBLIC = 3;

		// android.provider.Calendar.ExtendedPropertiesColumns
		public static final String EVENT_ID = "event_id";
//		public static final String EVENT_NAME = "name";   // variance: NAME
//		public static final String VALUE = "value";

		// android.provider.Calendar.Instances
		public static final String BEGIN = "begin";
//		public static final String INSTANCE_DEFAULT_SORT_ORDER = "begin ASC";   // variance: DEFAULT_SORT_ORDER
		public static final String END = "end";
//		public static final String END_DAY = "endDay";
//		public static final String END_MINUTE = "endMinute";
//		public static final String EVENT_ID = "event_id";	// Duplicate
//		public static final String SORT_CALENDAR_VIEW = "begin ASC, end DESC, title ASC";
//		public static final String START_DAY = "startDay";
//		public static final String START_MINUTE = "startMinute";
		
	// projection used to query events
	public static final String[] INSTANCES_PROJECTION = { 	
		EVENT_ID,			// 0
		TITLE,				// 1
		ALL_DAY,			// 2
		BEGIN,				// 3
		END,				// 4
		EVENT_LOCATION,		// 5
		DESCRIPTION,		// 6
		COLOR,				// 7
	};
	
	// class fields used to build uri's
//	public Uri mRemindersUri;
//	public Uri mEventsUri;
//	public Uri mCalendarsUri;
	public Uri mInstancesUri;
	
	public CalendarHelperBase() {
		String contentProvider;
		
		if (Build.VERSION.SDK_INT >= 8) {
			contentProvider = "com.android.calendar";
		} else {
			contentProvider = "calendar";
		}

//		mRemindersUri = Uri.parse(String.format("content://%s/reminders",contentProvider));
//		mEventsUri = Uri.parse(String.format("content://%s/events",contentProvider));
//		mCalendarsUri = Uri.parse(String.format("content://%s/calendars",contentProvider));
		mInstancesUri = Uri.parse(String.format("content://%s/instances/when",contentProvider));
	}
	
	@Override
	public Uri getInstancesUri() {
		return mInstancesUri;
	}

	@Override
	public String[] getInstancesProjection() {
		return INSTANCES_PROJECTION;
	}

}
