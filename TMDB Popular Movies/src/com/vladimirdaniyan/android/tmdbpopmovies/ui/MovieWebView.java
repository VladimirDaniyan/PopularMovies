package com.vladimirdaniyan.android.tmdbpopmovies.ui;


import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.vladimirdaniyan.android.tmdbpopmovies.R;

public class MovieWebView extends Activity {
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		Bundle extras = getIntent().getExtras();
		
		String url = extras.getString("url");
		webView = (WebView) findViewById(R.id.webView1);
//		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setUseWideViewPort(true);
		webView.setWebViewClient(new WebViewClient());
		
		webView.loadUrl(url);
	}

}
