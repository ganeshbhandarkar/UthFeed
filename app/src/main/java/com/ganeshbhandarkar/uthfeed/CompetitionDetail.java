package com.ganeshbhandarkar.uthfeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
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

public class CompetitionDetail extends AppCompatActivity {

    ImageView PostDetailImage;
    CircleImageView UserImage;
    TextView PostDetailContent,PostDetailDate,PostDetailTitle;
    EditText PostDetailComment;
    Button PostDetailCommentAddButton,RegisterButton;
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
        setContentView(R.layout.competition_detail);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        PostDetailImage = findViewById(R.id.competitionDetailImage);
        UserImage = findViewById(R.id.userImage);
        PostDetailContent = findViewById(R.id.competitionDetailContent);
        PostDetailDate = findViewById(R.id.competitionDetailDate);
        PostDetailTitle = findViewById(R.id.competitionDetailTitle);
        RegisterButton = findViewById(R.id.CompetitionRegisterButton);
        PostDetailComment = findViewById(R.id.postDetailComment);
        PostDetailCommentAddButton = findViewById(R.id.postDetailAddComment);
        recyclerView = findViewById(R.id.commentsRV);

        PostDetailCommentAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PostDetailCommentAddButton.setVisibility(View.INVISIBLE);
                DatabaseReference databaseReference = firebaseDatabase.getReference("Competition_Comments").child(PostKey).push();
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
                        Toast.makeText(CompetitionDetail.this, "Comment Added", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        PostDetailCommentAddButton.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
//        intent.putExtra("competitionRegister",mData.get(position).getRegisterLink());

        final String competitionRegister = getIntent().getExtras().getString("competitionRegister");

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(competitionRegister));
                startActivity(i);
            }
        });

        String competitionImage = getIntent().getExtras().getString("competitionImage");

        Glide.with(this).load(competitionImage).into(PostDetailImage);

        String competitionTitle = getIntent().getExtras().getString("competitionTitle");
        String competitionContent = getIntent().getExtras().getString("competitionDescription");

        PostDetailTitle.setText(competitionTitle);
        PostDetailContent.setText(competitionContent);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {

            Photo = acct.getPhotoUrl();
        }

        Glide.with(this).load(Photo).into(UserImage);


        PostKey = getIntent().getExtras().getString("competitionKey");

        String postTimeStamp = timeStamp(getIntent().getExtras().getLong("competitionDate"));


        PostDetailDate.setText(postTimeStamp);


        initRecyclerView();

    }


    private void initRecyclerView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference commentRef = firebaseDatabase.getReference("Competition_Comments").child(PostKey);
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
