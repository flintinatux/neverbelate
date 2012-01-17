/**
 * 
 */
package com.madhackerdesigns.neverlate;

import android.content.Context;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

/**
 * @author flintinatux
 *
 */
public class UserLocationOverlay extends MyLocationOverlay {

	private static final String TOAST_MESSAGE = "This is your current location";
	
	private Context mContext;
//	private MapOverlay mMapOverlay;
	
	/**
	 * @param context
	 * @param mapView
	 */
	public UserLocationOverlay(Context context, MapView mapView) {
		// Constructor from super class
		super(context, mapView);
		mContext = context;
//		Drawable flagGreen = context.getResources().getDrawable(R.drawable.flag_green);
//		mMapOverlay = new MapOverlay(context, flagGreen);
	}

//	/* (non-Javadoc)
//	 * @see com.google.android.maps.MyLocationOverlay#drawMyLocation(android.graphics.Canvas, com.google.android.maps.MapView, android.location.Location, com.google.android.maps.GeoPoint, long)
//	 */
//	@Override
//	protected void drawMyLocation(Canvas canvas, MapView mapView,
//			Location lastFix, GeoPoint myLocation, long when) {
//		// Let it draw the blue dot first underneath
//		super.drawMyLocation(canvas, mapView, lastFix, myLocation, when);
//		
//		// Draw our own green flag on top
//		mMapOverlay.clearOverlays();
//		OverlayItem overlay = new OverlayItem(myLocation, TOAST_MESSAGE, "");
//		mMapOverlay.addOverlay(overlay);
//		mapView.getOverlays().add(mMapOverlay);
//	}

	/* (non-Javadoc)
	 * @see com.google.android.maps.MyLocationOverlay#onTap(com.google.android.maps.GeoPoint, com.google.android.maps.MapView)
	 */
	@Override
	public boolean onTap(GeoPoint p, MapView map) {
		boolean tapped = super.onTap(p, map);
		// Toast that this is the user location
		if (tapped) {
			Toast.makeText(mContext, TOAST_MESSAGE, Toast.LENGTH_SHORT).show();
		}
		return tapped;
	}

	
}
