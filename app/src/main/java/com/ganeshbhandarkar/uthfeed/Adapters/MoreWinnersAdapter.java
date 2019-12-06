package com.ganeshbhandarkar.uthfeed.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ganeshbhandarkar.uthfeed.Models.MoreWinnersModel;
import com.ganeshbhandarkar.uthfeed.R;

import java.util.List;

public class MoreWinnersAdapter extends RecyclerView.Adapter<MoreWinnersAdapter.ViewHolder> {


    List<MoreWinnersModel> mData;
    Context mContext;
    View view;

    public MoreWinnersAdapter(List<MoreWinnersModel> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.more_winners_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.WinnerName.setText(mData.get(position).getWinnerName());
        holder.WinnerCompetition.setText(mData.get(position).getCompetitionName());
        Glide.with(mContext).load(mData.get(position).getImageUri()).into(holder.WinnerImage);


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
            WinnerCompetition = itemView.findViewById(R.id.winner_competition_name);

        }
    }
}
