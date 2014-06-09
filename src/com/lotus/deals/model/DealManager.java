package com.lotus.deals.model;

import java.util.ArrayList;
import java.util.List;

public class DealManager {

	private static final DealManager sharedInstance = new DealManager();  
	
	private List<Deal> deals = new ArrayList<Deal>();	
	
	private DealManager() {
		
	}
	
	public static DealManager sharedInstance() {
		return sharedInstance;
	}
	
	public List<Deal> getDeals(){
		return deals;
	}

	public Deal getDeal(int position) {
		return deals.get(position);
	}
	
	public void addDeals(List<Deal> deals) {
		// Check if duplicates
		// For now we are clearing the cache and setting the new data
		this.deals.clear();
		this.deals.addAll(deals);
	}
	
	public int getSize() {
		return deals.size();
	}
}