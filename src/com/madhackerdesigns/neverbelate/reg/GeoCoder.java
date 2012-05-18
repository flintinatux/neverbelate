package com.madhackerdesigns.neverbelate.reg;

import org.json.JSONException;
import org.json.JSONObject;

import com.madhackerdesigns.neverbelate.util.HttpRetriever;

public class GeoCoder {
	
	private static final String YAHOO_API_BASE_URL = "http://where.yahooapis.com/geocode?q=%1$s,+%2$s&flags=J&gflags=R&appid=lt2qlf34";
	
	private HttpRetriever httpRetriever = new HttpRetriever();
	
	public GeoCodeResult reverseGeoCode(double latitude, double longitude) {
		
		String url = String.format(YAHOO_API_BASE_URL, String.valueOf(latitude), String.valueOf(longitude));		
		String response = httpRetriever.retrieve(url);
		return parseJsonResponse(response);
		
	}
	
	private GeoCodeResult parseJsonResponse(String response) {
		GeoCodeResult result = new GeoCodeResult();
		try {
			JSONObject resultSet = (new JSONObject(response)).getJSONObject("ResultSet");
			int found = resultSet.getInt("Found");
			if (found > 0) {
				JSONObject firstResult = resultSet.getJSONArray("Results").getJSONObject(0);
				result.setCountryCode(firstResult.getString("countrycode"));
				result.setZipCode(firstResult.getString("uzip"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

}
