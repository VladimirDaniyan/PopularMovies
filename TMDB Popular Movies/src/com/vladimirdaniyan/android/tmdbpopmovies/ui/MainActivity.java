package com.vladimirdaniyan.android.tmdbpopmovies.ui;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.vladimirdaniyan.android.tmdbpopmovies.R;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener, ViewPager.OnPageChangeListener {

	SimplePagerAdapter mSimplePagerAdapter;

	ViewPager viewPager;

	private TextAndImageListFragment textAndImageListFragment;

	// private TextListFragment textListFragment;
	// private ImageListFragment imageListFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

//		FragmentManager fm = getSupportFragmentManager();

		viewPager = (ViewPager) findViewById(R.id.pager);

		viewPager
				.setAdapter(new SimplePagerAdapter(getSupportFragmentManager()));

		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		 actionBar.addTab(actionBar.newTab().setText(R.string.title_text_images)
		 .setTabListener(this));
		 actionBar.addTab(actionBar.newTab().setText(R.string.title_text_only)
		 .setTabListener(this));
		 actionBar.addTab(actionBar.newTab().setText(R.string.title_images_only)
		 .setTabListener(this));

		viewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class SimplePagerAdapter extends FragmentPagerAdapter {

		public SimplePagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@SuppressWarnings("static-access")
		@Override
		public Fragment getItem(int position) {
			return (textAndImageListFragment.newInstance(position));

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}

	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());

	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	public void onPageScrollStateChanged(int i) {
	}

	@Override
	public void onPageScrolled(int i, float v, int i1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int position) {
		getActionBar().setSelectedNavigationItem(position);

		int titleId = -1;
		switch (position) {
		case 1:
			titleId = R.string.title_text_images;
			break;
		case 2:
			titleId = R.string.title_text_only;
			break;
		case 3:
			titleId = R.string.title_images_only;
			break;
		}

	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

}
