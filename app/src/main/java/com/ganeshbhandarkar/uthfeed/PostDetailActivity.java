package com.ganeshbhandarkar.uthfeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ganeshbhandarkar.uthfeed.Adapters.CommentAdapter;
import com.ganeshbhandarkar.uthfeed.Models.CommentModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostDetailActivity extends AppCompatActivity {

    ImageView PostDetailImage;
    CircleImageView PostDetailAuthorImage,UserImage;
    TextView PostDetailContent,PostDetailDate,PostDetailTitle,PostAuthorName;
    EditText PostDetailComment;
    Button PostDetailCommentAddButton;
    Uri Photo;
    String PostKey;
    FirebaseUser mUser;
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    RecyclerView recyclerView;
    CommentAdapter commentAdapter;
    CommentModel commentModel;
    List<CommentModel> commentList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_detail);


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        PostDetailImage = findViewById(R.id.postDetailImage);
        PostDetailAuthorImage = findViewById(R.id.postDetailAuthorImage);
        UserImage = findViewById(R.id.userImage);
        PostDetailContent = findViewById(R.id.postDetailContent);
        PostDetailDate = findViewById(R.id.postDetailDate);
        PostDetailTitle = findViewById(R.id.postDetailTitle);
        PostDetailComment = findViewById(R.id.postDetailComment);
        PostDetailCommentAddButton = findViewById(R.id.postDetailAddComment);
        PostAuthorName = findViewById(R.id.postAuthorName);
        recyclerView = findViewById(R.id.commentsRV);



        PostDetailCommentAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PostDetailCommentAddButton.setVisibility(View.INVISIBLE);
                DatabaseReference databaseReference = firebaseDatabase.getReference("Comments").child(PostKey).push();
                String comment_content = PostDetailComment.getText().toString();
                String uid = mUser.getUid();
                String uname = mUser.getDisplayName();
                String uimg = mUser.getPhotoUrl().toString();
                CommentModel commentModel = new CommentModel(comment_content,uid,uimg,uname);

                databaseReference.setValue(commentModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        PostDetailCommentAddButton.setVisibility(View.VISIBLE);
                        PostDetailComment.setText("");
                        Toast.makeText(PostDetailActivity.this, "Comment Added", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        PostDetailCommentAddButton.setVisibility(View.VISIBLE);
                    }
                });
            }
        });


        // i.putExtra("postImage",mData.get(postion).getImageUri());
        //                    i.putExtra("postContent",mData.get(postion).getDescripton());
        //                    i.putExtra("postKey",mData.get(postion).getPostKey());
        //                    i.putExtra("postAuthor",mData.get(postion).getAuthor());

        String postAuthorName = getIntent().getExtras().getString("postAuthor");

        PostAuthorName.setText(postAuthorName);


        String postImage = getIntent().getExtras().getString("postImage");

        Glide.with(this).load(postImage).into(PostDetailImage);

        String postTitle = getIntent().getExtras().getString("postTitle");
        String postContent = getIntent().getExtras().getString("postContent");
        String postAuthor = getIntent().getExtras().getString("postAuthor");
        String postAuthorImage = getIntent().getExtras().getString("postAuthorImage");

        Glide.with(this).load(postAuthorImage).into(PostDetailAuthorImage);

        PostDetailTitle.setText(postTitle);
        PostDetailContent.setText(postContent);



        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {

            Photo = acct.getPhotoUrl();
        }

        Glide.with(this).load(Photo).into(UserImage);


        PostKey = getIntent().getExtras().getString("postKey");

        String postTimeStamp = timeStamp(getIntent().getExtras().getLong("postDate"));


        PostDetailDate.setText(postTimeStamp);


        initRecyclerView();



    }

    private void initRecyclerView() {

            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            DatabaseReference commentRef = firebaseDatabase.getReference("Comments").child(PostKey);
            commentRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    commentList = new ArrayList<>();

                    for(DataSnapshot commentPost : dataSnapshot.getChildren()){

                        CommentModel commentModel = commentPost.getValue(CommentModel.class);
                        commentList.add(commentModel);

                    }

                    commentAdapter = new CommentAdapter(commentList,getApplicationContext());
                    recyclerView.setAdapter(commentAdapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


    }

    private String timeStamp(long time){
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);

        String date = DateFormat.format("dd-MM-yyyy",calendar).toString();
        return date;
    }
}
