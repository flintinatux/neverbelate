/**
 * 
 */
package com.madhackerdesigns.neverbelate.service;

import android.net.Uri;
import android.os.Build;

/**
 * @author flintinatux
 *
 */
public abstract class CalendarHelper {

	public static final int PROJ_EVENT_ID = 0;
	public static final int PROJ_TITLE = 1;
	public static final int PROJ_ALL_DAY = 2;
	public static final int PROJ_BEGIN = 3;
	public static final int PROJ_END = 4;
	public static final int PROJ_EVENT_LOCATION = 5;
	public static final int PROJ_DESCRIPTION = 6;
	public static final int PROJ_COLOR = 7;

	public abstract Uri getInstancesUri();
	public abstract String[] getInstancesProjection();

	public static CalendarHelper createHelper() {
		if (Build.VERSION.SDK_INT >= 14) {
			return new CalendarHelperICS();
		} else {
			return new CalendarHelperBase();
		}
	}
	
}
