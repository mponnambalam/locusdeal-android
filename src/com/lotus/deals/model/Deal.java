package com.lotus.deals.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.Bitmap;

import com.lotus.deals.utils.JSONUtils;

public class Deal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3850459038057112802L;
	
	public long ID;
	public String affiliate;
	public String name;
	public String address;
	public String address2;
	public String storeID;
	public String chainID;
	public int totalDealsInThisStore;
	public String homepage;
	public String phone;
	public String state;
	public String city;
	public String ZIP;
	public String URL;
	public String storeURL;
	public String dealSource;
	public String user;
	public String userID;
	public int rating;
	public String dealTitle;
	public String disclaimer;
	public String dealinfo;
	public String expirationDate;
	public String postDate;
	public String showImage;
	public String showImageStandardBig;
	public String showImageStandardSmall;
	public String showLogo;
	public String up;
	public String down;
	public int DealTypeID;
	public int categoryID;
	public int subcategoryID;
	public double lat;
	public double lon;
	public double distance;
	public float dealOriginalPrice;
	public float dealPrice;
	public float dealSavings;
	public float dealDiscountPercent;

	public Bitmap bitmap;
	
	public static Deal fromJson(JSONObject jsonObject) {
		Deal deal = new Deal();
		deal.setName(JSONUtils.getString(jsonObject, "name"));
		deal.setAddress(JSONUtils.getString(jsonObject, "address"));
		deal.setAddress2(JSONUtils.getString(jsonObject, "address2"));
		deal.setAffiliate(JSONUtils.getString(jsonObject,"affiliate"));
		deal.setCategoryID(JSONUtils.getInt(jsonObject, "categoryID"));
		deal.setChainID(JSONUtils.getString(jsonObject, "chainID"));
		deal.setCity(JSONUtils.getString(jsonObject, "city"));
		deal.setDealDiscountPercent((float)JSONUtils.getDouble(jsonObject, "dealDiscountPercent"));
		deal.setDealinfo(JSONUtils.getString(jsonObject, "dealinfo"));
		deal.setDealOriginalPrice((float)JSONUtils.getDouble(jsonObject, "dealOriginalPrice"));
		deal.setDealPrice((float)JSONUtils.getDouble(jsonObject, "dealPrice"));
		deal.setDealSavings((float)JSONUtils.getDouble(jsonObject, "dealSavings"));
		deal.setDealSource(JSONUtils.getString(jsonObject, "dealSource"));
		deal.setDealTitle(JSONUtils.getString(jsonObject, "dealTitle"));
		deal.setDealTypeID(JSONUtils.getInt(jsonObject, "dealTypeID"));
		deal.setDisclaimer(JSONUtils.getString(jsonObject, "disclaimer"));
		deal.setDistance(JSONUtils.getDouble(jsonObject, "distance"));
		deal.setDown(JSONUtils.getString(jsonObject, "down"));
		deal.setExpirationDate(JSONUtils.getString(jsonObject, "expirationDate"));
		deal.setHomepage(JSONUtils.getString(jsonObject, "homepage"));
		deal.setID(JSONUtils.getLong(jsonObject, "ID"));
		deal.setLat(JSONUtils.getDouble(jsonObject, "lat"));
		deal.setLon(JSONUtils.getDouble(jsonObject, "lon"));
		deal.setPhone(JSONUtils.getString(jsonObject, "phone"));
		deal.setPostDate(JSONUtils.getString(jsonObject, "postDate"));
		deal.setShowImage(JSONUtils.getString(jsonObject, "showImage"));
		deal.setShowImageStandardBig(JSONUtils.getString(jsonObject, "showImageStandardBig"));
		deal.setShowImageStandardSmall(JSONUtils.getString(jsonObject, "showImageStandardSmall"));
		deal.setShowLogo(JSONUtils.getString(jsonObject, "showLogo"));
		deal.setState(JSONUtils.getString(jsonObject, "state"));
		deal.setStoreID(JSONUtils.getString(jsonObject, "storeID"));
		deal.setStoreURL(JSONUtils.getString(jsonObject, "storeURL"));
		deal.setSubcategoryID(JSONUtils.getInt(jsonObject, "subcategoryID"));
		deal.setTotalDealsInThisStore(JSONUtils.getInt(jsonObject, "totalDealsInThisStore"));
		deal.setUp(JSONUtils.getString(jsonObject, "up"));
		deal.setURL(JSONUtils.getString(jsonObject, "URL"));
		deal.setUser(JSONUtils.getString(jsonObject, "user"));
		deal.setUserID(JSONUtils.getString(jsonObject, "userID"));
		deal.setZIP(JSONUtils.getString(jsonObject, "ZIP"));
		
		deal.setRating((int)Math.round(Math.random() * 100)%5+1);
		
		return deal;
	}

	public static ArrayList<Deal> fromJSONArray(JSONArray jsonArray) {
		ArrayList<Deal> deals = new ArrayList<Deal>(jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject dealJson = null;
			
			try {
				dealJson = jsonArray.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			Deal deal = Deal.fromJson(dealJson);
			if (deal != null) {
				deals.add(deal);
			}
		}

		return deals;
	}
	
	// Getters and setters for the deal object
	
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public String getAffiliate() {
		return affiliate;
	}

	public void setAffiliate(String affiliate) {
		this.affiliate = affiliate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getStoreID() {
		return storeID;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

	public String getChainID() {
		return chainID;
	}

	public void setChainID(String chainID) {
		this.chainID = chainID;
	}

	public int getTotalDealsInThisStore() {
		return totalDealsInThisStore;
	}

	public void setTotalDealsInThisStore(int totalDealsInThisStore) {
		this.totalDealsInThisStore = totalDealsInThisStore;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZIP() {
		return ZIP;
	}

	public void setZIP(String zIP) {
		ZIP = zIP;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getStoreURL() {
		return storeURL;
	}

	public void setStoreURL(String storeURL) {
		this.storeURL = storeURL;
	}

	public String getDealSource() {
		return dealSource;
	}

	public void setDealSource(String dealSource) {
		this.dealSource = dealSource;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getDealTitle() {
		return dealTitle;
	}

	public void setDealTitle(String dealTitle) {
		this.dealTitle = dealTitle;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public String getDealinfo() {
		return dealinfo;
	}

	public void setDealinfo(String dealinfo) {
		this.dealinfo = dealinfo;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getShowImage() {
		return showImage;
	}

	public void setShowImage(String showImage) {
		this.showImage = showImage;
	}

	public String getShowImageStandardBig() {
		return showImageStandardBig;
	}

	public void setShowImageStandardBig(String showImageStandardBig) {
		this.showImageStandardBig = showImageStandardBig;
	}

	public String getShowImageStandardSmall() {
		return showImageStandardSmall;
	}

	public void setShowImageStandardSmall(String showImageStandardSmall) {
		this.showImageStandardSmall = showImageStandardSmall;
	}

	public String getShowLogo() {
		return showLogo;
	}

	public void setShowLogo(String showLogo) {
		this.showLogo = showLogo;
	}

	public String getUp() {
		return up;
	}

	public void setUp(String up) {
		this.up = up;
	}

	public String getDown() {
		return down;
	}

	public void setDown(String down) {
		this.down = down;
	}

	public int getDealTypeID() {
		return DealTypeID;
	}

	public void setDealTypeID(int dealTypeID) {
		DealTypeID = dealTypeID;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public int getSubcategoryID() {
		return subcategoryID;
	}

	public void setSubcategoryID(int subcategoryID) {
		this.subcategoryID = subcategoryID;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public float getDealOriginalPrice() {
		return dealOriginalPrice;
	}

	public void setDealOriginalPrice(float dealOriginalPrice) {
		this.dealOriginalPrice = dealOriginalPrice;
	}

	public float getDealPrice() {
		return dealPrice;
	}

	public void setDealPrice(float dealPrice) {
		this.dealPrice = dealPrice;
	}

	public float getDealSavings() {
		return dealSavings;
	}

	public void setDealSavings(float dealSavings) {
		this.dealSavings = dealSavings;
	}

	public float getDealDiscountPercent() {
		return dealDiscountPercent;
	}

	public void setDealDiscountPercent(float dealDiscountPercent) {
		this.dealDiscountPercent = dealDiscountPercent;
	}

}
