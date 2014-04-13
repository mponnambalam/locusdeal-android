package com.lotus.deals.fragments.publish;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lotus.deals.R;
import com.lotus.deals.model.Deal;

public class PublishFragmentPage3 extends Fragment {

	private Deal deal;
	private PublishFragmentPage3Listener listener;
	
	public interface PublishFragmentPage3Listener {
		public void previewButtonPage3Clicked();
		public void uploadButtonPage3Clicked();
	}
	
	public PublishFragmentPage3() {
		// TODO Auto-generated constructor stub
	}
	
	public static PublishFragmentPage3 newInstance(Deal deal) {
		PublishFragmentPage3 publishFragmentPage3 = new PublishFragmentPage3();
        Bundle args = new Bundle();
        args.putSerializable("deal", deal);
        publishFragmentPage3.setArguments(args);
        return publishFragmentPage3;
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_publish_page3, container, false);
      return view;
    }

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		if(activity instanceof PublishFragmentPage3Listener) {
			this.listener = (PublishFragmentPage3Listener) activity;
		} else {
			throw new ClassCastException(activity.toString()
		            + " must implement MyListFragment.OnItemSelectedListener");
		}
	}
	
}
