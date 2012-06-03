package com.madhackerdesigns.neverbelate.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class BuildMode {
	
	private static Boolean DEBUG;
	
	public static boolean isDebug(Context context) {
		if (DEBUG == null) {
			try {
				String packageName = context.getPackageName();
				PackageInfo pinfo = context.getPackageManager().getPackageInfo(packageName, 0);
				DEBUG = (pinfo.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
			} catch (NameNotFoundException e) {
				e.printStackTrace();
				DEBUG = false;
			}
		}
		
		return DEBUG;
	}

}
