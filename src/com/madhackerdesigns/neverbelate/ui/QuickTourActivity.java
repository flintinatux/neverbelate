package com.madhackerdesigns.neverbelate.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madhackerdesigns.neverbelate.R;

public class QuickTourActivity extends FragmentActivity {
	
	static final int[] PAGE_LAYOUTS = {	
		R.layout.qt_page_0,
		R.layout.qt_page_1,
		R.layout.qt_page_2,
		R.layout.qt_page_3,
		R.layout.qt_page_4
	};
	
	static final int[] PAGE_TITLES = {
		R.string.qt_title_0,
		R.string.qt_title_1,
		R.string.qt_title_2,
		R.string.qt_title_3,
		R.string.qt_title_4
	};
	
	static Resources RESOURCES;

    MyAdapter mAdapter;
    ViewPager mPager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quick_tour);

        mAdapter = new MyAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        
        RESOURCES = getResources();
    }
    
    public static class MyAdapter extends FragmentStatePagerAdapter {
        
    	public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return PAGE_LAYOUTS.length;
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(position);
        }

		@Override
		public CharSequence getPageTitle(int position) {
			return RESOURCES.getString(PAGE_TITLES[position]);
		}
    }
    
    public static class PageFragment extends Fragment {

    	private int mPosition;
    	
    	/**
         * Create a new instance of PageFragment, providing "pos"
         * as an argument.
         */
        public static PageFragment newInstance(int position) {
			PageFragment f = new PageFragment();
			
			// Supply pager position as an argument.
            Bundle args = new Bundle();
            args.putInt("pos", PAGE_LAYOUTS[position]);
            f.setArguments(args);

            return f;
		}
		
		/**
         * When creating, retrieve this instance's position from its arguments.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mPosition = getArguments() != null ? getArguments().getInt("pos") : 1;
        }

        /**
         * The Fragment's UI is inflated from the list of layouts.
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View v = inflater.inflate(mPosition, container, false);
            TextView skipTour = (TextView) v.findViewById(R.id.tv_skip_tour);
            if (skipTour != null) {
	            skipTour.setText(Html.fromHtml(v.getResources().getString(R.string.qt_skip_tour)));
	            skipTour.setOnClickListener(new OnClickListener() {
	
					public void onClick(View v) {
						// Finish the activity
						getActivity().finish();
					}
	            	
	            });
            }
            return v;
        }
    }
}
