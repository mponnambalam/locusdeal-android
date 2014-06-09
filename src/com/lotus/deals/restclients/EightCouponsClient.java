package com.lotus.deals.restclients;

import org.json.JSONArray;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.lotus.deals.model.Deal;
import com.lotus.deals.model.DealManager;

public class EightCouponsClient {
	
	private static EightCouponsClient sharedInstance = new EightCouponsClient();
	
	private EightCouponsClient() {
		
	}
	
	public EightCouponsClient sharedInstance() {
		return sharedInstance;
	}
	
	public void populateDealsFromRegion(String zip) {
		String requestUrl;
		requestUrl = "http://api.8coupons.com/v1/getdeals?key=b115affda61e93374155aca0aeb6adf1c8e16e46cf992bd46201021556d3b2dff5b18ee48b51c78e7ceaff54649ad4c2&zip="+zip+"&mileradius=5&limit=10&orderby=radius";
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(requestUrl, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonArray) {
				/*
				deals.clear();
				deals = Deal.fromJSONArray(jsonArray);
				dealsAdapter.clear();
				dealsAdapter.addAll(deals);
				*/
				Log.d("DEBUG", "size of response json:"+jsonArray.length());
				DealManager.sharedInstance().addDeals(Deal.fromJSONArray(jsonArray));
			}
		});
	}
}
