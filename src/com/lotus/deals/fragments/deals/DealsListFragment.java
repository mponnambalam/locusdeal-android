package com.lotus.deals.fragments.deals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lotus.deals.R;
import com.lotus.deals.activities.DealDetailActivity;
import com.lotus.deals.adapters.DealsAdapter;
import com.lotus.deals.model.Deal;
import com.lotus.deals.model.DealManager;

public class DealsListFragment extends ListFragment {
	DealsAdapter dealsAdapter;
	ListView lvDeals;
	DealManager dealManager;

    // Container Activity must implement this interface
	// Display the deal detail activity on click
    public interface OnDealSelectedListener {
        public void onDealSelected(int position);
    }
	
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	Intent i = new Intent(getView().getContext(), DealDetailActivity.class);
    	Deal deal = DealManager.sharedInstance().getDeal(position);
		i.putExtra("deal", deal);
		startActivity(i);
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		      Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_deals_list, container, false);
        
        dealManager = DealManager.sharedInstance();
        dealManager.getDeals().clear();
        
        dealsAdapter = new DealsAdapter(getActivity(), dealManager.getDeals());
        
		lvDeals = (ListView) view.findViewById(android.R.id.list);
		lvDeals.setAdapter(dealsAdapter);
		
		setListAdapter(dealsAdapter);
		
		// return the view
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		dealsAdapter.notifyDataSetChanged();
	}
}
