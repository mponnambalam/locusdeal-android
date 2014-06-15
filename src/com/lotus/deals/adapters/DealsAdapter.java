package com.lotus.deals.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lotus.deals.R;
import com.lotus.deals.model.Deal;
import com.lotus.deals.utils.ImageUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class DealsAdapter extends ArrayAdapter<Deal> {
	
	Context context;

	public DealsAdapter(Context context, List<Deal> deals) {
		super(context, 0, deals);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.cell_deal_item, null);
		}

		Deal deal = getItem(position);

		String finalImageUrl = ImageUtils.getFinalRedirectedUrl(deal
				.getShowImageStandardSmall());

		ImageView imageView = (ImageView) view.findViewById(R.id.ivStoreImage);
		ImageLoader imageLoader = ImageLoader.getInstance();
		
		//http://stackoverflow.com/questions/12434895/why-this-error-occured-java-lang-runtimeexception-imageloader-must-be-init-w
		// Have to initiliase the default configuration, else throws
		// Illegal State Exception
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
		imageLoader.displayImage(finalImageUrl, imageView);

		TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
		tvTitle.setText(deal.getDealTitle());

		TextView tvStoreName = (TextView) view.findViewById(R.id.tvStoreName);
		tvStoreName.setText("at " + deal.getName());

		// TextView postedDate = (TextView)
		// view.findViewById(R.id.tvPostedDate);
		// postedDate.setText("Posted: "+deal.getPostDate());

		TextView expiryDate = (TextView) view.findViewById(R.id.tvExpiryDate);
		expiryDate.setText("Expires: " + deal.getExpirationDate());

		TextView tvDealSource = (TextView) view.findViewById(R.id.tvDealSource);
		tvDealSource.setText(deal.getDealSource());

		Button tvRating = (Button) view.findViewById(R.id.btnRating);
		tvRating.setText(Integer.toString(deal.getRating()));

		return view;
	}
}
