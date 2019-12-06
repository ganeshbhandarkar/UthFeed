package com.ganeshbhandarkar.uthfeed.Compete;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ganeshbhandarkar.uthfeed.R;

import java.util.List;

public class WinnerAdapter extends RecyclerView.Adapter<WinnerAdapter.ViewHolder> {

    List<WinnerModel> mData;
    Context mContext;
    View view;

    public WinnerAdapter(List<WinnerModel> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.winner_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.WinnerName.setText(mData.get(position).getWinnerName());
        holder.WinnerCompetition.setText(mData.get(position).getCompetitionName());
//        Glide.with(holder.WinnerImage.getContext()).load(mData.get(position).getImageUri()).apply(new RequestOptions().placeholder(R.drawable.trophy_placeholder)).into(holder.WinnerImage);
        Glide.with(mContext).load(mData.get(position).getImageUri()).apply(new RequestOptions().placeholder(R.drawable.trophy_placeholder)).into(holder.WinnerImage);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView WinnerImage;
        TextView WinnerName;
        TextView WinnerCompetition;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            WinnerImage = itemView.findViewById(R.id.winner_image);
            WinnerName = itemView.findViewById(R.id.winner_name);
            WinnerCompetition = itemView.findViewById(R.id.competition_name);

        }
    }
}
