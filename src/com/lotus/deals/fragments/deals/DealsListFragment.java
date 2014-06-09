package com.lotus.deals.fragments.deals;

import org.json.JSONArray;

import com.lotus.deals.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.lotus.deals.activities.DealDetailActivity;
import com.lotus.deals.adapters.DealsAdapter;
import com.lotus.deals.model.Deal;
import com.lotus.deals.model.DealManager;

public class DealsListFragment extends ListFragment {
	DealsAdapter dealsAdapter;
	ListView lvDeals;
	private int categoryID = 0;
	private String zipCode = "94103";
	DealManager dealManager;

    // Container Activity must implement this interface
    public interface OnDealSelectedListener {
        public void onDealSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
	
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	Intent i = new Intent(getView().getContext(), DealDetailActivity.class);
		i.putExtra("position", position);
		startActivity(i);
    }
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		      Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_deals_list, container, false);
        
        dealManager = DealManager.getInstance();
        dealsAdapter = new DealsAdapter(getActivity(), dealManager.getDeals());
		
        dealManager.getDeals().clear();
        
		lvDeals = (ListView) view.findViewById(android.R.id.list);
		lvDeals.setAdapter(dealsAdapter);

		setListAdapter(dealsAdapter);
		
		// return view;
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		int id = getArguments().getInt("categoryID", 0);
		String zip = getArguments().getString("zip", "94103");
		
		if(categoryID == id && zipCode == zip && dealManager.getSize() > 0) {
			dealsAdapter.notifyDataSetChanged();
		} else {
			categoryID = id;
			zipCode = zip;
			loadDeals(id, zip);
		}
	}
	
	public void loadDeals(int id, String zip) {
		String requestUrl;
		categoryID = id;
		
		setListShown(false);
		
		if(categoryID < 1) {
			requestUrl = "http://api.8coupons.com/v1/getdeals?key=b115affda61e93374155aca0aeb6adf1c8e16e46cf992bd46201021556d3b2dff5b18ee48b51c78e7ceaff54649ad4c2&zip="+zip+"&mileradius=5&limit=10&orderby=radius";
		} else {
			requestUrl = "http://api.8coupons.com/v1/getdeals?key=b115affda61e93374155aca0aeb6adf1c8e16e46cf992bd46201021556d3b2dff5b18ee48b51c78e7ceaff54649ad4c2&zip="+zip+"&mileradius=5&limit=10&orderby=radius&categoryid="+categoryID;
		}

		Log.d("DEBUG", "list fragment requestUrl:"+requestUrl);
		fetchDeals(requestUrl);
	}
	
	public void fetchDeals(String requestUrl) {
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
				dealManager.getDeals().clear();
				dealManager.addDeals(Deal.fromJSONArray(jsonArray));
				dealsAdapter.notifyDataSetChanged();
				setListShown(true);
			}
		});
	}
}
