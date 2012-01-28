/**
 * 
 */
package com.madhackerdesigns.neverbelate.ui;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

/**
 * @author flintinatux
 *
 */
public class MapOverlay extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;
	
	public MapOverlay(Context context, Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;
	}
	
	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}
	
	public void clearOverlays() {
		mOverlays.clear();
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}
	
	@Override
	protected boolean onTap(int index) {
		OverlayItem item = mOverlays.get(index);
		String title = item.getTitle();
		String snippet = item.getSnippet();
		if (notEmpty(title)) { Toast.makeText(mContext, title, Toast.LENGTH_SHORT).show(); }
		if (notEmpty(snippet)) { Toast.makeText(mContext, item.getSnippet(), Toast.LENGTH_SHORT).show(); }
		return true;
	}
	
	private boolean notEmpty(String s) {
		return (s != null && s.length() > 0);
	}
}
