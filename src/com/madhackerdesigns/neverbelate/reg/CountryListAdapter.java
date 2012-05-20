/**
 * 
 */
package com.madhackerdesigns.neverbelate.reg;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * @author smccormack
 *
 */
public class CountryListAdapter extends ResourceCursorAdapter {

	private Context mContext;
	
	public CountryListAdapter(Context context, int layout, Cursor c, boolean autoRequery) {
		super(context, layout, c, autoRequery);
		
		// Store away the context and an inflater
		this.mContext = context;
	}

	/* (non-Javadoc)
	 * @see android.widget.CursorAdapter#bindView(android.view.View, android.content.Context, android.database.Cursor)
	 */
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// Retrieve the ViewHolder with which to bind the cursor data
		ViewHolder holder = (ViewHolder) view.getTag();
		
		// Harvest the data from the cursor
		String name = cursor.getString(CountriesDB.PROJ_NAME);
		String code = cursor.getString(CountriesDB.PROJ_CODE);
		
		// Set the country name
		holder.name.setText(name);
		holder.code = code;
	}

	/* (non-Javadoc)
	 * @see android.widget.CursorAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Cursor cursor = getCursor();
		cursor.moveToPosition(position);
		View view = convertView;
		
		if (view == null) {
			// If the convertView is null, inflate a new one (is also bound to cursor)
			view = newView(mContext, cursor, parent);
			ViewHolder holder = new ViewHolder();
			holder.name = (TextView) view.findViewById(android.R.id.text1);
			view.setTag(holder);
		}
		
		// Bind the view to the data, and return it
		bindView(view, mContext, cursor);
		return view;
	}

	protected static class ViewHolder {
		TextView name;
		String code;
	}

}
