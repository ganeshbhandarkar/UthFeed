package com.ganeshbhandarkar.uthfeed.Videos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ganeshbhandarkar.uthfeed.MainFragments.MediaFragment;
import com.ganeshbhandarkar.uthfeed.R;

public class FullScreenVideo extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen_video);

        webView = findViewById(R.id.video_view_full);



        String link = getIntent().getStringExtra("link");

        webView.loadUrl(link);

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.clearCache(true);
        webView.getSettings().getJavaScriptEnabled();
//            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);



    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//
//        Intent i = new Intent(FullScreenVideo.this, MediaFragment.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(i);
//    }
}
