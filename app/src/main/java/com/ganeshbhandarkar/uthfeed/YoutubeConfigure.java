package com.ganeshbhandarkar.uthfeed;
//
//public class YoutubeConfigure {
//
//    public YoutubeConfigure(){
//
//    }
//
//    private static final String API_KEY = "AIzaSyB4DcSlnqlvTjqx8tZTbGGzB4sSZ4ScgTM";
//    String API_KEY="AIzaSyD-h1cUKszfKyIfxXQjNzdgijX1ENw2d4E"
//    public static String getApiKey(){
//        return API_KEY;
//    }
//}

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.VideoView;


import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeConfigure extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    VideoView videoView;
    Uri uri;
    Intent intent;
    YouTubePlayerView youTubePlayerView;
    String API_KEY="AIzaSyB4DcSlnqlvTjqx8tZTbGGzB4sSZ4ScgTM";
    private static final int RECOVERY_REQUEST = 1;

//    private String checkVideo(int id){
//        String URl="";
//        if(id==1){
//            URl = "nvH7RZzLQp0";
//        }else if(id==2){
//            URl = "eJHSltYXsIk";
//        }else if(id==3){
//            URl = "fae3nVb5cIE";
//        }else if(id==4){
//            URl = "XWa4HvRA9sA";
//        }else if(id==5){
//            URl = "GBvQvi79Fw4";
//        }else if(id==6){
//            URl = "QXWcqp9sUbY";
//        }
//        return URl;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        youTubePlayerView = findViewById(R.id.youtubeVideoView);
        youTubePlayerView.initialize(API_KEY, this);
        intent = getIntent();
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        int video = intent.getIntExtra("video",0);
        String url = intent.getStringExtra("video");
//        String url = checkVideo(video);
        youTubePlayer.cueVideo(url);
    }
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            Toast.makeText(this, youTubeInitializationResult.toString(), Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            getYouTubePlayerProvider().initialize(API_KEY, this);
        }
    }
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubePlayerView;
    }

}

