package com.ganeshbhandarkar.uthfeed.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ganeshbhandarkar.uthfeed.Models.PostModel;
import com.ganeshbhandarkar.uthfeed.PostDetailActivity;
import com.ganeshbhandarkar.uthfeed.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {


    Context mContext;
    List<PostModel> mData;
    View view;

    public PostAdapter(Context mContext, List<PostModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.read_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.readPostTitle.setText(mData.get(position).getTitle());
        holder.readPostAuthor.setText(mData.get(position).getAuthor());
        Glide.with(mContext).load(mData.get(position).getImageUri()).apply(new RequestOptions().placeholder(R.drawable.leaflet)).into(holder.readPostImage);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView readPostTitle,readPostAuthor;
        ImageView readPostImage;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            readPostTitle = itemView.findViewById(R.id.read_item_title);
            readPostAuthor = itemView.findViewById(R.id.author_item_name);
            readPostImage = itemView.findViewById(R.id.read_item_image);

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
