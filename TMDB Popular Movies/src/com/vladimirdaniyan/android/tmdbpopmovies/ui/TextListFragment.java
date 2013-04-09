package com.vladimirdaniyan.android.tmdbpopmovies.ui;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.vladimirdaniyan.android.tmdbpopmovies.R;
import com.vladimirdaniyan.android.tmdbpopmovies.util.JSONParser;
import com.vladimirdaniyan.android.tmdbpopmovies.util.LazyAdapter;

public class TextListFragment extends ListFragment  {
	
	private static final String KEY_POSITION = "position";
	private static final String TAG = "TVDB Pop Movies";
	
	private static final String apiKey = "?api_key=57983e31fb435df4df77afb854740ea9n";
	private static final String tmdbURL = "http://api.themoviedb.org";
	
	private static final String TAG_MOVIES = "results";
	private static final String TAG_ID = "id";
	private static final String TAG_RELEASE = "release_date";
	private static final String TAG_TITLE = "title";
	private static final String TAG_POSTER = "poster_path";
	private static final String TAG_VOTE_AVG = "vote_average";
	private static final String TAG_VOTE_COUNT = "vote_count";

	ArrayList<HashMap<String, String>> mylist;
	JSONObject json = null;
	JSONArray results = null;

	public static Fragment newInstance(int position) {
		TextAndImageListFragment frag = new TextAndImageListFragment();
		Bundle args = new Bundle();
		args.putInt(KEY_POSITION, position);
		frag.setArguments(args);
		return frag;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View result = inflater.inflate(R.layout.list_items, container, false);
		// int position = getArguments().getInt(KEY_POSITION, -1);

		mylist = new ArrayList<HashMap<String, String>>();

		new DownloadJSON().execute();
		return result;
	}

	private class DownloadJSON extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... url) {
			json = JSONParser
					.getJSONfromURL(tmdbURL + "/3/movie/popular" + apiKey);
			
			Log.d(TAG, "the url is " + tmdbURL + "/3/movie/popular" + apiKey );

			try {
				// Get the array of movies
				results = json.getJSONArray(TAG_MOVIES);
				
				// loop through all the movies
				for (int i = 0; i < results.length(); i++) {
					HashMap<String, String> map = new HashMap<String, String>();
					JSONObject r = results.getJSONObject(i);

					String id = r.getString(TAG_ID);
					String title = r.getString(TAG_TITLE);
					String poster = r.getString(TAG_POSTER);
					String release = r.getString(TAG_RELEASE);
					String vote = r.getString(TAG_VOTE_AVG);
					String voteCount = r.getString(TAG_VOTE_COUNT);

					map.put(TAG_ID, id);
					map.put(TAG_TITLE, title);
					map.put(TAG_POSTER, poster);
					map.put(TAG_RELEASE, release);
					map.put(TAG_VOTE_AVG, vote);
					map.put(TAG_VOTE_COUNT, voteCount);

					mylist.add(map);

					// Log.v("JSON", "output " + map);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			LazyAdapter adapter = new LazyAdapter(getActivity(), mylist);
			setListAdapter(adapter);
			super.onPostExecute(result);
		}

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		openWebView(position);

	}

	void openWebView(int position) {
		String movieURL = tmdbURL + "/movie/" + mylist.get(getId());
		Intent intent = new Intent(getActivity(), MovieWebView.class);
		intent.putExtra("url", movieURL);
		startActivity(intent);
		
		Log.d(TAG, "the url for the movie is " + movieURL );
		Log.d(TAG, "movie id is " + mylist.get(getId()) );
	}

}
