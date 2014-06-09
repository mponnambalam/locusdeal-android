package com.lotus.deals.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ImageUtils {
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
}
