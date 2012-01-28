package com.madhackerdesigns.neverbelate.provider;


public class AlertsHelper {
	/**
     * Standard projection for all columns of a normal alert.
     */
    public static final String[] ALERT_PROJECTION = new String[] {
            AlertsContract.Alerts._ID,              //  0 - alert id
            AlertsContract.Alerts.EVENT_ID,			//  1 - event id
            AlertsContract.Alerts.CALENDAR_COLOR,	//  2 - calendar color
            AlertsContract.Alerts.TITLE,  			//  3 - event title
            AlertsContract.Alerts.BEGIN,			//  4 - event instance begin time
            AlertsContract.Alerts.END,				//  5 - event instance end time
            AlertsContract.Alerts.LOCATION,			//  6 - event location
            AlertsContract.Alerts.DESCRIPTION,		//  7 - event description
            AlertsContract.Alerts.DURATION,			//  8 - travel duration time
            AlertsContract.Alerts.COPYRIGHTS,		//  9 - maps data copyrights
            AlertsContract.Alerts.JSON,				// 10 - json-formatted directions to event
            AlertsContract.Alerts.FIRED,			// 11 - flag: alert fired?
            AlertsContract.Alerts.DISMISSED,		// 12 - flag: alert dismissed?
    };
    public static final int PROJ_ID = 0;
    public static final int PROJ_EVENT_ID = 1;
    public static final int PROJ_CALENDAR_COLOR = 2;
    public static final int PROJ_TITLE = 3;
    public static final int PROJ_BEGIN = 4;
    public static final int PROJ_END = 5;
    public static final int PROJ_LOCATION = 6;
    public static final int PROJ_DESCRIPTION = 7;
    public static final int	PROJ_DURATION = 8;
    public static final int PROJ_COPYRIGHTS = 9;
    public static final int PROJ_JSON = 10;
    public static final int PROJ_FIRED = 11;
    public static final int PROJ_DISMISSED = 12;
}
