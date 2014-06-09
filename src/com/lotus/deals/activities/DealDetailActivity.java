package com.lotus.deals.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lotus.deals.R;
import com.lotus.deals.model.Deal;
import com.lotus.deals.utils.ImageUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DealDetailActivity extends Activity {
	
	private Deal deal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deal_detail);
		
		Intent intent = getIntent();
		deal = (Deal) intent.getSerializableExtra("deal");
		
		refresh();
	}

	private void refresh() {
		
		String finalImageUrl = ImageUtils.getFinalRedirectedUrl(deal.getShowImageStandardBig());
        
		ImageView imageView = (ImageView) findViewById(R.id.sivImage);
		imageView.setImageResource(0);
        ImageLoader.getInstance().displayImage(finalImageUrl, imageView);
		
		TextView tvTitle = (TextView) findViewById(R.id.dealTitleTV);
		tvTitle.setText(deal.getName());
		
		TextView tvExpires = (TextView) findViewById(R.id.expiresTV);
		tvExpires.setText("Expires "+deal.getExpirationDate());
		
		TextView tvAddress = (TextView) findViewById(R.id.tvAddress);
		String address = "";
		String address1 = deal.getAddress();
		String address2 = deal.getAddress2();
		String city = deal.getCity();
		String state = deal.getState();
		String zip = deal.getZIP();
		String phoneNumber = deal.getPhone();
		
		if(address1 != null) {
			address += address1;
		}
		if(address2 != null) {
			address += " " + address2;
		}
		if(city != null) {
			address += " " + city;
		}
		if(state != null) {
			address += " " + state;
		}
		if(zip != null) {
			address += " " + zip;
		}
		if(phoneNumber != null) {
			address += " (Phone# " +phoneNumber+" )";
		}
		tvAddress.setText(address);
		
		TextView tvDealInfo = (TextView) findViewById(R.id.tvDealInfo);
		tvDealInfo.setText(deal.getDealinfo());
		
		TextView tvDealValue = (TextView) findViewById(R.id.dealValueTV);
		
		String dealValue = "";
		
		if(deal.getDealDiscountPercent() > 0) {
			dealValue = deal.getDealDiscountPercent() + "% OFF";
		} else if(deal.getDealSavings()>0)  {
			dealValue="Savings: $"+ deal.getDealSavings();
		} 
		
		tvDealValue.setText(dealValue);
		
		TextView tvDetail = (TextView) findViewById(R.id.detailsTV);
		tvDetail.setText(deal.getDealTitle());
		
        Button tvRating = (Button) findViewById(R.id.btnRating);
        tvRating.setText(Integer.toString(deal.getRating()));
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.deal_detail, menu);
//		return true;
//	}
}
