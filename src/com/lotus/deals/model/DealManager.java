package com.lotus.deals.model;

import java.util.ArrayList;
import java.util.List;

public class DealManager {

	private static final DealManager sharedInstance = new DealManager();  
	
	private List<Deal> deals = new ArrayList<Deal>();	
	
	private DealManager() {
		
	}
	
	public static DealManager getInstance() {
		return sharedInstance;
	}
	
	public List<Deal> getDeals(){
		return deals;
	}

	public Deal getDeal(int position) {
		return deals.get(position);
	}
	
	public void addDeals(List<Deal> deals) {
		this.deals.addAll(deals);
	}
	
	public int getSize() {
		return deals.size();
	}
}