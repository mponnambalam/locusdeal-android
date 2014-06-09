package com.lotus.deals.fragments.publish;

import com.lotus.deals.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.lotus.deals.model.Deal;

public class PublishFragmentPage1 extends Fragment {
	
	private Deal deal;
	private PublishFragmentPage1Listener listener;
	
	private EditText name;
	private EditText address;
	private Spinner dealType;
	
	public interface PublishFragmentPage1Listener {
		public void continueButtonPage1Clicked(Deal deal);
	}

	/*
	 * Factory method to return an instance with the deal parameter
	 */
	public static PublishFragmentPage1 newInstance(Deal deal) {
		PublishFragmentPage1 publishFragmentPage1 = new PublishFragmentPage1();
        Bundle args = new Bundle();
        args.putSerializable("deal", deal);
        publishFragmentPage1.setArguments(args);
        return publishFragmentPage1;
    }
	
	/*
	 * (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onAttach(android.app.Activity)
	 * Attach the activity as the listener for the buttons in this fragment
	 */
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		if(activity instanceof PublishFragmentPage1Listener) {
			this.listener = (PublishFragmentPage1Listener) activity;
		} else {
			throw new ClassCastException(activity.toString()
		            + " must implement MyListFragment.OnItemSelectedListener");
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 * Called when the fragment is expected to render the view
	 * Attach the UI elements
	 */
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_publish_page1, container, false);
      
      name = (EditText) view.findViewById(R.id.name);
      address = (EditText) view.findViewById(R.id.address);
      dealType = (Spinner) view.findViewById(R.id.dealType);
      
      return view;
    }
	
	/*
	 * (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreate(android.os.Bundle)
	 * Retreive the deal instance
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		deal = (Deal) getArguments().getSerializable("deal");
	}
	
	/*
	 * The continue button onClick set in the XML file
	 */
	public void continueButtonPressed(Button continueButton) {
		// Validate the data in the inputs
		if(validateData()) {
			deal.setName(name.getText().toString());
			deal.setAddress(address.getText().toString());
			deal.setDealTitle(dealType.getSelectedItem().toString());
			listener.continueButtonPage1Clicked(deal);
		} else {
			Context context = getActivity().getApplicationContext();
	        Toast.makeText(context, "Validation failed", Toast.LENGTH_SHORT).show();
		}
	}
	
	/*
	 * Validate the data that is supplied in the fragment
	 */
	public boolean validateData() {
		String nameString = name.getText().toString();
		String addressString = address.getText().toString();
		String dealTypeString = dealType.getSelectedItem().toString();
		
		if(nameString == null || nameString.isEmpty()) {
			return false;
		}
		
		if(addressString == null || addressString.isEmpty()) {
			return false;
		}
		
		if(dealTypeString == null || dealTypeString.isEmpty()) {
			return false;
		}
		
		return true;
	}

}
