package com.lotus.deals.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.lotus.deals.R;
import com.lotus.deals.fragments.publish.PublishFragmentPage1;
import com.lotus.deals.fragments.publish.PublishFragmentPage1.PublishFragmentPage1Listener;
import com.lotus.deals.fragments.publish.PublishFragmentPage2;
import com.lotus.deals.fragments.publish.PublishFragmentPage2.PublishFragmentPage2Listener;
import com.lotus.deals.fragments.publish.PublishFragmentPage3;
import com.lotus.deals.fragments.publish.PublishFragmentPage3.PublishFragmentPage3Listener;
import com.lotus.deals.model.Deal;

public class PublishActivity extends FragmentActivity implements PublishFragmentPage1Listener, PublishFragmentPage2Listener, PublishFragmentPage3Listener {
	
	FrameLayout publishFrameLayout;
	Deal deal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_publish);
		Deal deal = new Deal();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		PublishFragmentPage3 publishFragmentPage3 = PublishFragmentPage3.newInstance(deal);
//		PublishFragmentPage1 publishFragmentPage1 = PublishFragmentPage1.newInstance(deal);
		ft.replace(R.id.publishFrameLayout, publishFragmentPage3);
		ft.commit();
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.publish, menu);
//		return true;
//	}

	@Override
	public void continueButtonPage1Clicked(Deal dealFromFragment) {
		deal = dealFromFragment;
		// Load the next page...
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		PublishFragmentPage2 publishFragmentPage2 = PublishFragmentPage2.newInstance(deal);
		ft.replace(R.id.publishFrameLayout, publishFragmentPage2);
		ft.commit();
	}
	
	@Override
	public void continueButtonPage2Clicked(Deal dealFromFragment) {
		deal = dealFromFragment;
		// Load the next page...
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		PublishFragmentPage3 publishFragmentPage3 = PublishFragmentPage3.newInstance(deal);
		ft.replace(R.id.publishFrameLayout, publishFragmentPage3);
		ft.commit();
	}

	@Override
	public void previewButtonPage3Clicked(Deal dealFromFragment) {
		Intent i = new Intent(this, DealDetailActivity.class);
		i.putExtra("deal", deal);
		startActivity(i);
	}

	@Override
	public void uploadButtonPage3Clicked(Deal dealFromFragment) {
		Toast.makeText(this, "Todo: Send data to server", Toast.LENGTH_SHORT).show();
	}
}
