/**
 * 
 */
package com.madhackerdesigns.neverbelate.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.madhackerdesigns.neverbelate.R;

/**
 * @author flintinatux
 *
 */
public class UserLocationOverlay extends MyLocationOverlay {

	private static final String TOAST_MESSAGE = "This is your current location";
	
	private Context mContext;
	private ArrayList<OverlayItem> mDest;
	private MapView mMapView;
	private GeoPoint mOrig;
	
	/**
	 * @param context
	 * @param mapView
	 */
	public UserLocationOverlay(Context context, MapView mapView) {
		// Constructor from super class
		super(context, mapView);
		mContext = context;
		mMapView = mapView;
	}
	
	public void drawLocations() {
		drawLocations(null);
	}
	
	public void drawLocations(Location current) {
		final Context context = mContext;
		final MapView mapView = mMapView;
		
		// Use origin point if current is null
		GeoPoint userLoc;
		if (current != null) {
			int lat = (int) (1.0E6 * current.getLatitude());
			int lon = (int) (1.0E6 * current.getLongitude());
			userLoc = new GeoPoint(lat, lon);
		} else {
			userLoc = mOrig;
		}
		
		// Add the user location to the map bounds
		MapBounds bounds = new MapBounds();
		bounds.addPoint(userLoc.getLatitudeE6(), userLoc.getLongitudeE6());
		
		// Create an overlay with a blue flag for the user location
		Drawable blueDrawable = context.getResources().getDrawable(R.drawable.flag_blue);
		MapOverlay blueOverlay = new MapOverlay(context, blueDrawable);
		blueOverlay.addOverlay(new OverlayItem(userLoc, null, null));
		
		// Add the blue overlay
		List<Overlay> mapOverlays = mapView.getOverlays();
		mapOverlays.clear();
		mapOverlays.add(blueOverlay);
		
		// Always rebuild red overlay to get the bounds correct
		Drawable redDrawable = context.getResources().getDrawable(R.drawable.flag_red);
		MapOverlay redOverlay = new MapOverlay(context, redDrawable);
		final Iterator<OverlayItem> destIterator = mDest.iterator();
		do {
			OverlayItem item = destIterator.next();
			redOverlay.addOverlay(item);
			GeoPoint dest = item.getPoint();
			bounds.addPoint(dest.getLatitudeE6(), dest.getLongitudeE6());
		} while (destIterator.hasNext());
		mapOverlays.add(redOverlay); 
		
		// Get map controller, animate to zoom center, and set zoom span
		final MapController mapController = mapView.getController();
		GeoPoint zoomCenter = new GeoPoint(bounds.getLatCenterE6(), bounds.getLonCenterE6());
		mapController.animateTo(zoomCenter);
		mapController.zoomToSpan(bounds.getLatSpanE6(), bounds.getLonSpanE6());
	}

	/* (non-Javadoc)
	 * @see com.google.android.maps.MyLocationOverlay#onLocationChanged(android.location.Location)
	 */
	@Override
	public synchronized void onLocationChanged(Location location) {
		super.onLocationChanged(location);
		
		// If we are ready, then draw the locations
		if (mDest != null && mOrig != null) {
			drawLocations(location);
		}
	}

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
	
	public void addDestination(GeoPoint dest, String title, String location) {
		mDest.add(new OverlayItem(dest, title, location));
	}

	public void setOrigin(GeoPoint orig) {
		mOrig = orig;
	}
	
	private class MapBounds {
		private int mLatMinE6 = 0;
		private int mLatMaxE6 = 0;
		private int mLonMinE6 = 0;
		private int mLonMaxE6 = 0;
		private static final double RATIO = 1.25;
		
		protected MapBounds() {
			super();
		}
		
		protected void addPoint(int latE6, int lonE6) {
			// Check if this latitude is the minimum
			if (mLatMinE6 == 0 || latE6 < mLatMinE6) { mLatMinE6 = latE6; }
			// Check if this latitude is the maximum
			if (mLatMaxE6 == 0 || latE6 > mLatMaxE6) { mLatMaxE6 = latE6; }
			// Check if this longitude is the minimum
			if (mLonMinE6 == 0 || lonE6 < mLonMinE6) { mLonMinE6 = lonE6; }
			// Check if this longitude is the maximum
			if (mLonMaxE6 == 0 || lonE6 > mLonMaxE6) { mLonMaxE6 = lonE6; }
		}
		
		protected int getLatSpanE6() {
			return (int) Math.abs(RATIO * (mLatMaxE6 - mLatMinE6));
		}
		
		protected int getLonSpanE6() {
			return (int) Math.abs(RATIO * (mLonMaxE6 - mLonMinE6));
		}
		
		protected int getLatCenterE6() {
			return (int) (mLatMaxE6 + mLatMinE6)/2;
		}
		
		protected int getLonCenterE6() {
			return (int) (mLonMaxE6 + mLonMinE6)/2;
		}
	}
}
