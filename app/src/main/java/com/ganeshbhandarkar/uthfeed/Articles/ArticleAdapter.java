package com.ganeshbhandarkar.uthfeed.Articles;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ganeshbhandarkar.uthfeed.R;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    Context mContext;
    List<ArticleModel> articleModelList;


    public ArticleAdapter(Context mContext, List<ArticleModel> articleModelList) {
        this.mContext = mContext;
        this.articleModelList = articleModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.article_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.articleImage.setImageResource(articleModelList.get(position).getArticleImage());
        holder.heading.setText(articleModelList.get(position).getArticleHeading());
        holder.author.setText(articleModelList.get(position).getArticleAuthor());
        holder.articleLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(articleModelList.get(position).getArticleUrl()));
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articleModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView articleImage;
        TextView heading,author;
        CardView articleLink;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            articleImage = itemView.findViewById(R.id.articleItemImage);
            heading = itemView.findViewById(R.id.articleItemHeading);
            author = itemView.findViewById(R.id.articleItemAuthor);
            articleLink = itemView.findViewById(R.id.articleLink);
        }
    }
}
