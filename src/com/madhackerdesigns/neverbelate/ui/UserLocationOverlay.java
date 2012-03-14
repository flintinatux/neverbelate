/**
 * 
 */
package com.madhackerdesigns.neverbelate.ui;

import android.content.Context;
import android.location.Location;
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
	private OnLocationChangedListener mListener;
	
	/**
	 * @param context
	 * @param mapView
	 */
	public UserLocationOverlay(Context context, MapView mapView) {
		// Constructor from super class
		super(context, mapView);
		mContext = context;
	}

	/* (non-Javadoc)
	 * @see com.google.android.maps.MyLocationOverlay#onLocationChanged(android.location.Location)
	 */
	@Override
	public synchronized void onLocationChanged(Location location) {
		super.onLocationChanged(location);
		
		// Also callback to the listener
		if (mListener != null) { mListener.onLocationChanged(location); }
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
	
	public void setOnLocationChangedListener(OnLocationChangedListener listener) {
		mListener = listener;
	}
	
	public interface OnLocationChangedListener {
		
		/**
		 * @param location	The current location of the user.
		 */
		public void onLocationChanged(Location location);
		
	}
}
