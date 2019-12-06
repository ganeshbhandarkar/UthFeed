package com.ganeshbhandarkar.uthfeed.Videos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ganeshbhandarkar.uthfeed.R;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {


    Context mContext;
    List<VideoModel> youtubeVideoList;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.video_item,parent,false);

        return new ViewHolder(view);
    }

    public VideoAdapter(Context mContext, List<VideoModel> youtubeVideoList) {
        this.mContext = mContext;
        this.youtubeVideoList = youtubeVideoList;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final VideoModel current = youtubeVideoList.get(position);

        holder.webView.loadUrl(current.getVideoLink());
        holder.fullScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,FullScreenVideo.class);
                i.putExtra("link",current.getVideoLink());
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return youtubeVideoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        WebView webView;
        Button fullScreenButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fullScreenButton = itemView.findViewById(R.id.fullVideoButton);
            webView = itemView.findViewById(R.id.video_view);
            webView.setWebViewClient(new WebViewClient());
            webView.setWebChromeClient(new WebChromeClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAllowFileAccess(true);
            webView.clearCache(true);
            webView.getSettings().getJavaScriptEnabled();
//            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);


        }
    }
}
