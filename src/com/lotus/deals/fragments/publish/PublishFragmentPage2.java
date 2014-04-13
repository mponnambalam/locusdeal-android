package com.lotus.deals.fragments.publish;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.lotus.deals.R;
import com.lotus.deals.model.Deal;

public class PublishFragmentPage2 extends Fragment {
	
	private Deal deal;
	private PublishFragmentPage2Listener listener;
	
	private EditText description;
	private DatePicker startDate;
	private TimePicker startTime;
	private DatePicker endDate;
	private TimePicker endTime;
	
	public interface PublishFragmentPage2Listener {
		public void backButtonPage2Clicked();
		public void continueButtonPage2Clicked();
	}	

	public PublishFragmentPage2() {
		// TODO Auto-generated constructor stub
	}
	
	public static PublishFragmentPage2 newInstance(Deal deal) {
		PublishFragmentPage2 publishFragmentPage2 = new PublishFragmentPage2();
        Bundle args = new Bundle();
        args.putSerializable("deal", deal);
        publishFragmentPage2.setArguments(args);
        return publishFragmentPage2;
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_publish_page2, container, false);
      
      description = (EditText) view.findViewById(R.id.description);
      startDate = (DatePicker) view.findViewById(R.id.startDate);
      startTime = (TimePicker) view.findViewById(R.id.startTime);
      endDate = (DatePicker) view.findViewById(R.id.endDate);
      endTime = (TimePicker) view.findViewById(R.id.endTime);
      
      return view;
    }
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		if(activity instanceof PublishFragmentPage2Listener) {
			this.listener = (PublishFragmentPage2Listener) activity;
		} else {
			throw new ClassCastException(activity.toString()
		            + " must implement MyListFragment.OnItemSelectedListener");
		}
	}

}
