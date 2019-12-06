package com.ganeshbhandarkar.uthfeed.Articles;

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
import com.ganeshbhandarkar.uthfeed.PostDetailActivity;
import com.ganeshbhandarkar.uthfeed.R;

import java.util.List;

public class ArticlePostAdapter extends RecyclerView.Adapter<ArticlePostAdapter.ViewHolder> {


    View view;
    List<ArticlePostModel> mData;
    Context mContext;

    public ArticlePostAdapter(List<ArticlePostModel> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.article_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.ArticleHeading.setText(mData.get(position).getTitle());
        holder.ArticleAuthorName.setText(mData.get(position).getAuthor());
        Glide.with(mContext).load(mData.get(position).getImageUri()).into(holder.ArticleImage);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ArticleImage;
        TextView ArticleHeading,ArticleAuthorName;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ArticleImage = itemView.findViewById(R.id.articleItemImage);
            ArticleHeading = itemView.findViewById(R.id.articleItemHeading);
            ArticleAuthorName = itemView.findViewById(R.id.articleItemAuthor);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent i = new Intent(mContext, PostDetailActivity.class);
                    int postion = getAdapterPosition();

                    i.putExtra("postTitle",mData.get(postion).getTitle());
                    i.putExtra("postImage",mData.get(postion).getImageUri());
                    i.putExtra("postAuthorImage",mData.get(postion).getAuthorUri());
                    i.putExtra("postContent",mData.get(postion).getDescripton());
                    i.putExtra("postKey",mData.get(postion).getPostKey());
                    i.putExtra("postAuthor",mData.get(postion).getAuthor());


                    long timeStamp = (long) mData.get(postion).getTimeStamp();

                    i.putExtra("postDate",timeStamp);

                    mContext.startActivity(i);


                }
            });
        }
    }
}
