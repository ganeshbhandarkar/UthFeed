package com.ganeshbhandarkar.uthfeed.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ganeshbhandarkar.uthfeed.CompetitionDetail;
import com.ganeshbhandarkar.uthfeed.Models.MoreCompetitionModel;
import com.ganeshbhandarkar.uthfeed.R;

import java.util.List;

public class MoreCompetitionAdapter extends RecyclerView.Adapter<MoreCompetitionAdapter.ViewHolder> {

    View view;
    List<MoreCompetitionModel> mData;
    Context mContext;

    public MoreCompetitionAdapter(List<MoreCompetitionModel> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.more_competition_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.CompetitionName.setText(mData.get(position).getTitle());
        Glide.with(mContext).load(mData.get(position).getImageUri()).into(holder.CompetitionImage);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView CompetitionImage;
        TextView CompetitionName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            CompetitionImage = itemView.findViewById(R.id.competition_image);
            CompetitionName = itemView.findViewById(R.id.competiton_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(mContext, CompetitionDetail.class);
                    int position = getAdapterPosition();

                    intent.putExtra("competitionTitle",mData.get(position).getTitle());
                    intent.putExtra("competitionImage",mData.get(position).getImageUri());
                    intent.putExtra("competitionDescription",mData.get(position).getDescription());
                    intent.putExtra("competitionRegister",mData.get(position).getRegisterLink());
                    intent.putExtra("competitionKey",mData.get(position).getPostKey());


                    long timeStamp = (long) mData.get(position).getTimeStamp();

                    intent.putExtra("competitionDate",timeStamp);

                    mContext.startActivity(intent);
                }
            });
        }
    }
}