package com.lotus.deals.restclients;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class YelpClient {
	String CONSUMER_KEY = "zEFotDgjwomL8xJJ0b_PWw";
	String CONSUMER_SECRET = "BAepddqrVKtjDLaStG8pfwfOlII";
	String TOKEN = "9ygb9HBYzRUtXQGADww6l9miLy8NSYFc";
	String TOKEN_SECRET = "JVQUl2oivVmfpMrKhZa4PWo_MAA";
	
	private OAuthService service;
	private Token accessToken;
	
	private static final YelpClient sharedInstance = new YelpClient();
	
	private YelpClient() {
		service = new ServiceBuilder().provider(YelpV2API.class).apiKey(CONSUMER_KEY).apiSecret(CONSUMER_SECRET).build();
		accessToken = new Token(TOKEN, TOKEN_SECRET);
	}
	
	public static YelpClient getSharedInstance() {
		return sharedInstance;
	}
	
	public void getDeals() {
		// Execute a signed call to the Yelp service.  
		OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
		// request.addQuerystringParameter("ll", lat + "," + lng);
		// request.addQuerystringParameter("category", category);
		service.signRequest(accessToken, request);
		Response response = request.send();
		String rawData = response.getBody();
		// Use GSON to convert raw data to the Java object
	}
}
