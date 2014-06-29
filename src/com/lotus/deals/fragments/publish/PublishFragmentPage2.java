package com.lotus.deals.fragments.publish;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lotus.deals.R;
import com.lotus.deals.model.Deal;

public class PublishFragmentPage2 extends Fragment implements OnClickListener {

	private Deal deal;
	private PublishFragmentPage2Listener listener;

	private EditText description;
	private DatePicker startDate;
	private TimePicker startTime;
	private DatePicker endDate;
	private TimePicker endTime;

	public interface PublishFragmentPage2Listener {
		public void continueButtonPage2Clicked(Deal deal);
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

		Button continueButton = (Button) view.findViewById(R.id.continueButtonPage2);
		continueButton.setOnClickListener(this);

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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		deal = (Deal) getArguments().getSerializable("deal");
	}

	/*
	 * Validate the data that is supplied in the fragment
	 */
	public boolean validateData() {
		String descriptionString = description.getText().toString();
		String startDateString = getDateFromDatePicker(startDate);
		String startTimeString = getTimeFromTimePicker(startTime);

		String endDateString = getDateFromDatePicker(endDate);
		String endTimeString = getTimeFromTimePicker(endTime);


		if(descriptionString == null || descriptionString.isEmpty()) {
			return false;
		}

		if(startDateString == null || startDateString.isEmpty()) {
			return false;
		}

		if(startTimeString == null || startTimeString.isEmpty()) {
			return false;
		}

		if(endDateString == null || endDateString.isEmpty()) {
			return false;
		}

		if(endTimeString == null || endTimeString.isEmpty()) {
			return false;
		}

		return true;
	}

	//	http://stackoverflow.com/questions/8409043/getdate-from-datepicker-android
	public static String getDateFromDatePicker(DatePicker datePicker){
		int day = datePicker.getDayOfMonth();
		int month = datePicker.getMonth();
		int year =  datePicker.getYear();

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		String dateString = sdf.format(calendar.getTime());

		return dateString;
	}

	public static String getTimeFromTimePicker(TimePicker timePicker){
		int hour = timePicker.getCurrentHour();
		int minutes = timePicker.getCurrentMinute();

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minutes);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		String timeString = sdf.format(calendar.getTime());

		return timeString;
	}

	@Override
	public void onClick(View v) {
		// Validate the data in the inputs
		if(validateData()) {
			String descriptionString = description.getText().toString();
			String startDateString = getDateFromDatePicker(startDate);
			String startTimeString = getTimeFromTimePicker(startTime);

			String endDateString = getDateFromDatePicker(endDate);
			String endTimeString = getTimeFromTimePicker(endTime);

			deal.description = descriptionString;
			deal.startDate = startDateString;
			deal.startTime = startTimeString;

			deal.expirationDate = endDateString;
			deal.expirationTime = endTimeString;

			listener.continueButtonPage2Clicked(deal);
		} else {
			Context context = getActivity().getApplicationContext();
			Toast.makeText(context, "Validation failed", Toast.LENGTH_SHORT).show();
		}
	}

}
