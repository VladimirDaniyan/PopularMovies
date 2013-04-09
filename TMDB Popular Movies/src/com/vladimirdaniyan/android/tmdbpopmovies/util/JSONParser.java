package com.vladimirdaniyan.android.tmdbpopmovies.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {

	public static JSONObject getJSONfromURL(String url) {
		InputStream is = null;
		String result = "";
		JSONObject jObj = null;
		
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeader("Accept", "application/json");
			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

		} catch (Exception e) {
			Log.e("LOG_TAG", "Error in http connection " + e.toString());
		}

		// convert response to string
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
		} catch (Exception e) {
			Log.e("LOG_TAG", "Error converting result " + e.toString());
		}

		try {

			jObj = new JSONObject(result);
		} catch (JSONException e) {
			Log.e("LOG_TAG", "Error parsing data " + e.toString());
		}

		return jObj;
	}
}
