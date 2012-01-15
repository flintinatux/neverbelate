/**
 * 
 */
package com.madhackerdesigns.neverlate;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * @author smccormack
 *
 */
public class EventListAdapter extends ResourceCursorAdapter {

	private Context mContext;
//	private LayoutInflater mInflater;
	private int mDateTimeFlags = 0;
	private String mMinutesAway;
	
	public EventListAdapter(Context context, int layout, Cursor c, boolean autoRequery) {
		super(context, layout, c, autoRequery);
		
		// Store away the context and an inflater
		this.mContext = context;
//		this.mInflater = LayoutInflater.from(context);
		
		// Pre-load some resources to save time later
		mDateTimeFlags |= DateUtils.FORMAT_SHOW_TIME;
		mDateTimeFlags |= DateUtils.FORMAT_12HOUR;
		Resources res = context.getResources();
		mMinutesAway = res.getString(R.string.unit_minutes) + "\n" + res.getString(R.string.away_text);
	}

	/* (non-Javadoc)
	 * @see android.widget.CursorAdapter#bindView(android.view.View, android.content.Context, android.database.Cursor)
	 */
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// Retrieve the ViewHolder with which to bind the cursor data
		ViewHolder holder = (ViewHolder) view.getTag();
		
		// Harvest the data from the cursor
		int color = cursor.getInt(AlertsHelper.PROJ_CALENDAR_COLOR);
		String title = cursor.getString(AlertsHelper.PROJ_TITLE);
		long begin = cursor.getLong(AlertsHelper.PROJ_BEGIN);
		long end = cursor.getLong(AlertsHelper.PROJ_END);
		String location = cursor.getString(AlertsHelper.PROJ_LOCATION);
		long duration = cursor.getLong(AlertsHelper.PROJ_DURATION);
//		String json = cursor.getString(AlertsHelper.PROJ_JSON);
		
		// Set the color of the vertical stripe
		holder.stripe.setBackgroundColor(color);
		
		// Set the event title
		holder.title.setTextColor(color);
		holder.title.setText(title);
		holder.title.setEllipsize(TextUtils.TruncateAt.valueOf("MARQUEE"));
		holder.title.setMarqueeRepeatLimit(3);
		
		// Set the event time
		String eventTimeString = DateUtils.formatDateTime(context, begin, mDateTimeFlags);
		eventTimeString += " - " + DateUtils.formatDateTime(context, end, mDateTimeFlags);
		holder.when.setText(eventTimeString);
		
		// Set the event location
		holder.where.setText(location);
		holder.where.setEllipsize(TextUtils.TruncateAt.valueOf("MARQUEE"));
		holder.where.setMarqueeRepeatLimit(3);
		
		// Set the travel time in approximate minutes (remember: duration provided in seconds)
		holder.travelTime.setText((int)(duration / 60000) + " " + mMinutesAway);
	}

	/* (non-Javadoc)
	 * @see android.widget.CursorAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Cursor cursor = getCursor();
		cursor.moveToPosition(position);
		ViewHolder holder = new ViewHolder();
		View view = convertView;
		
		if (view == null) {
			// If the convertView is null, inflate a new one (is also bound to cursor)
			view = newView(mContext, cursor, parent);
			holder.stripe = (View) view.findViewById(R.id.vertical_stripe);
			holder.title = (TextView) view.findViewById(R.id.event_title);
			holder.when = (TextView) view.findViewById(R.id.event_time);
			holder.where = (TextView) view.findViewById(R.id.event_location);
			holder.travelTime = (TextView) view.findViewById(R.id.travel_time);
			view.setTag(holder);
		} else {
			// Otherwise, pull the holder from the existing convertView
//			holder = (ViewHolder) view.getTag();
		}
		
		// Bind the view to the data, and return it
		bindView(view, mContext, cursor);
		return view;
	}

	/* (non-Javadoc)
	 * @see android.widget.CursorAdapter#newView(android.content.Context, android.database.Cursor, android.view.ViewGroup)
	 */
//	@Override
//	public View newView(Context context, Cursor cursor, ViewGroup parent) {
//		// Inflate a new view, attach a ViewHolder, and bind the cursor data
//		View view = mInflater.inflate(R.layout.event_list_item, parent);
//		ViewHolder holder = new ViewHolder();
//		holder.stripe = (View) view.findViewById(R.id.vertical_stripe);
//		holder.title = (TextView) view.findViewById(R.id.event_title);
//		holder.when = (TextView) view.findViewById(R.id.event_time);
//		holder.where = (TextView) view.findViewById(R.id.event_location);
//		holder.travelTime = (TextView) view.findViewById(R.id.travel_time);
//		view.setTag(holder);
//		bindView(view, context, cursor);
//		return view;
//	}
	
	private static class ViewHolder {
		View stripe;
		TextView title;
		TextView when;
		TextView where;
		TextView travelTime;
	}

}
