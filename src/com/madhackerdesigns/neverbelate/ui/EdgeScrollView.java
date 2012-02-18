package com.madhackerdesigns.neverbelate.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.madhackerdesigns.neverbelate.R;

public class EdgeScrollView extends ScrollView {

	private static int edgeColor;
	
	public EdgeScrollView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public EdgeScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setVerticalFadingEdgeEnabled(true);
		setFadingEdgeLength(20);
		edgeColor = context.getResources().getColor(R.color.blue_electric);
	}

	@Override
	public int getSolidColor() {
		return edgeColor;
	}

}
