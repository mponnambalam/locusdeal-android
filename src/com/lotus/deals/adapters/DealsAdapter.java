package com.lotus.deals.adapters;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lotus.deals.R;
import com.lotus.deals.model.Deal;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DealsAdapter extends ArrayAdapter<Deal> {
	
	public DealsAdapter(Context context, List<Deal> deals) {
		super(context, 0, deals);
	}
	
	public static String getFinalRedirectedUrl(String url)
	{
	    HttpURLConnection connection = null;
	    String finalUrl = url;
	    try {
			do {
			        try {
						connection = (HttpURLConnection) new URL(finalUrl).openConnection();
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        connection.setInstanceFollowRedirects(false);
			        connection.setUseCaches(false);
			        try {
						connection.setRequestMethod("GET");
					} catch (ProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        try {
						connection.connect();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        int responseCode = 0;
					try {
						responseCode = connection.getResponseCode();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        if (responseCode >=300 && responseCode <400)
			        {
			            String redirectedUrl = connection.getHeaderField("Location");
			            if(null== redirectedUrl)
			                break;
			            finalUrl =redirectedUrl;
			                                System.out.println( "redirected url: " + finalUrl);
			        }
			        else
			            break;
			    }while (connection.getResponseCode() != HttpURLConnection.HTTP_OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    connection.disconnect();
	    return finalUrl;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    View view = convertView;
	    if (view == null) {
	    	LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    	view = inflater.inflate(R.layout.deal_item, null);
	    }
	    
        Deal deal = getItem(position);
        
        String finalImageUrl = getFinalRedirectedUrl(deal.getShowImageStandardSmall());
        
        ImageView imageView = (ImageView) view.findViewById(R.id.ivStoreImage);
        ImageLoader.getInstance().displayImage(finalImageUrl, imageView);
        
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvTitle.setText(deal.getDealTitle());

        TextView tvStoreName = (TextView) view.findViewById(R.id.tvStoreName);
        tvStoreName.setText("at "+deal.getName());
        
        // TextView postedDate = (TextView) view.findViewById(R.id.tvPostedDate);
        // postedDate.setText("Posted: "+deal.getPostDate());
        
        TextView expiryDate = (TextView) view.findViewById(R.id.tvExpiryDate);
        expiryDate.setText("Expires: " +deal.getExpirationDate());
        
        TextView tvDealSource = (TextView) view.findViewById(R.id.tvDealSource);
        tvDealSource.setText(deal.getDealSource());
		
        Button tvRating = (Button) view.findViewById(R.id.btnRating);
        tvRating.setText(Integer.toString(deal.getRating()));
        
        return view;
	}
}
