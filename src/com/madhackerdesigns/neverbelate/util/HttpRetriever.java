package com.madhackerdesigns.neverbelate.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class HttpRetriever {
	
	private final static String TAG = "HttpRetriever";

	private static DefaultHttpClient client = new DefaultHttpClient();

	public static String retrieve(String url) {

		HttpGet get = new HttpGet(url);

		try {

			HttpResponse getResponse = client.execute(get);
			HttpEntity getResponseEntity = getResponse.getEntity();

			if (getResponseEntity != null) {
				String response = EntityUtils.toString(getResponseEntity);
				Log.d(TAG, response);
				return response;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

}
