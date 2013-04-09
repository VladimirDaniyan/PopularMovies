package com.vladimirdaniyan.android.tmdbpopmovies.util;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vladimirdaniyan.android.tmdbpopmovies.R;

public class LazyAdapter extends BaseAdapter {

	private Context context;
	ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater = null;
	public ImageLoader imageLoader;
	public ImageView poster;
	private HashMap<String, String> mylist;
	public String url;
	public String posterPath;

	public LazyAdapter(Context a, ArrayList<HashMap<String, String>> d) {
		context = a;
		data = d;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(context.getApplicationContext());
	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.list_row, null);

		TextView title = (TextView) vi.findViewById(R.id.textView1);
		TextView vote = (TextView) vi.findViewById(R.id.textView2);
		TextView voteCount = (TextView) vi.findViewById(R.id.textView3);
		TextView release = (TextView) vi.findViewById(R.id.textView4);
		poster = (ImageView) vi.findViewById(R.id.imageView1);

		mylist = new HashMap<String, String>();
		mylist = data.get(position);

		title.setText((CharSequence) mylist.get("title"));
		vote.setText((CharSequence) mylist.get("vote_average"));
		voteCount.setText((CharSequence) mylist.get("vote_count"));
		release.setText((CharSequence) mylist.get("release_date"));

		new DownloadImage().execute();

		return vi;
	}

	public class DownloadImage extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... string) {

			
			posterPath = (String) mylist.get("poster_path");
			url = "http://d3gtl9l2a4fn1j.cloudfront.net/t/p/w185" + posterPath;
			return null;

		}

		@Override
		protected void onPostExecute(String result) {
			imageLoader.DisplayImage(url, poster);
			super.onPostExecute(result);
		}
	}
}