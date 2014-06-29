package com.lotus.deals.activities;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.lotus.deals.R;
import com.lotus.deals.fragments.deals.DealsListFragment;
import com.lotus.deals.fragments.deals.DealsMapFragment;
import com.lotus.deals.model.DealManager;

public class DealsHomeActivity extends FragmentActivity implements TabListener {

	EditText etLocation;
    Tab tabList;
    Tab tabMap;
    DealsListFragment listFragment;
    DealsMapFragment mapFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DealManager.sharedInstance();
		setContentView(R.layout.activity_deals_home);
		setupNavigationTabs();
		
		// The Strict mode is to remove the Android OS Network on Main Thread exception
		// Ideally we have to use Async Task or otherwise for the Networking Task
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}

	private void setupNavigationTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		tabList = actionBar.newTab().setText("Deals")
				.setTag("DealsListFragment").setIcon(R.drawable.ic_action_list)
				.setTabListener(this);

		tabMap = actionBar.newTab().setText("Map")
				.setTag("DealsMapFragment").setIcon(R.drawable.ic_action_map)
				.setTabListener(this);

		actionBar.addTab(tabList);
		actionBar.addTab(tabMap);
		actionBar.selectTab(tabList);
		
	}

	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
		
	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		FragmentManager manager = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction fts = manager
				.beginTransaction();
        
		if (tab.getTag() == "DealsListFragment") {
			listFragment = new DealsListFragment();
			fts.replace(R.id.frame_container, listFragment);
		} else {
			mapFragment = new DealsMapFragment();
			fts.replace(R.id.frame_container, mapFragment);
		}

		fts.commit();
	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		Log.d("DEBUG", "tab unselected:"+tab.getTag());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_deals_home, menu);
	    return true;
	}
	
	public void onPublishButtonClicked(MenuItem menuItem) {
		Intent i = new Intent(this, PublishActivity.class);
		startActivity(i);
	}
	
	
}


