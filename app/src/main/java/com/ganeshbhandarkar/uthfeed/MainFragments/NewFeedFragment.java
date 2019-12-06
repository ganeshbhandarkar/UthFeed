package com.ganeshbhandarkar.uthfeed.MainFragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ganeshbhandarkar.uthfeed.Compete.CompetitionAdapter;
import com.ganeshbhandarkar.uthfeed.Compete.CompetitionModel;
import com.ganeshbhandarkar.uthfeed.Compete.MoreCompetitionsActivity;
import com.ganeshbhandarkar.uthfeed.Compete.MoreWinnersActivity;
import com.ganeshbhandarkar.uthfeed.Compete.WinnerAdapter;
import com.ganeshbhandarkar.uthfeed.Compete.WinnerModel;
import com.ganeshbhandarkar.uthfeed.MainThreadActivities.GeneralLoginActivity;
import com.ganeshbhandarkar.uthfeed.Models.FlipperImageModel;
import com.ganeshbhandarkar.uthfeed.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class NewFeedFragment extends Fragment {

    private ImageView profiledialog;

    Dialog myDialog;
    FirebaseAuth mAuth;
    Button close,MoreCompetition,MoreWinners;
    Button logout,privacyPolicy;
    FlipperLayout flipperLayout;
    TextView UserName,UserEmail;
    ImageView UserProfileImage;

    View view;
//    String url[] = new String[3];

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
    DatabaseReference databaseReference2;

    List<CompetitionModel> competitionsList;
    List<WinnerModel> winnersList;

    RecyclerView recyclerView1;
    RecyclerView recyclerView2;

    CompetitionAdapter competitionAdapter;
    WinnerAdapter winnerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_newfeed,container,false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Competition_Posts");
        databaseReference1 = firebaseDatabase.getReference("Winners_Posts");
        databaseReference2 = firebaseDatabase.getReference("Images_Flipper_Posts");


        recyclerView1 = view.findViewById(R.id.recyclerView1);
//        CompetitionAdapter competitionAdapter = new CompetitionAdapter(competitionsList,getContext());
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true);
//        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView1.setLayoutManager(linearLayoutManager);
//        recyclerView1.setAdapter(competitionAdapter);



        recyclerView2 = view.findViewById(R.id.recyclerView2);
//        WinnerAdapter winnerAdapter = new WinnerAdapter(winnersList,getContext());
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
//        recyclerView2.setAdapter(winnerAdapter);
//

        init();
        return view;





    }

    private void init() {

        profiledialog = view.findViewById(R.id.profile_dialog);
        MoreCompetition = view.findViewById(R.id.more_competitions);
        MoreWinners = view.findViewById(R.id.more_winners);

        MoreCompetition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MoreCompetitionsActivity.class);
                startActivity(i);
            }
        });

        MoreWinners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MoreWinnersActivity.class);
                startActivity(i);
            }
        });

        profiledialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.show();
            }
        });


        // Image Flipper

        flipperLayout = view.findViewById(R.id.image_flipper);
        setLayout();

        //profile Dialog

        mAuth = FirebaseAuth.getInstance();


    }

    private void setLayout() {

        databaseReference2.limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){





                    FlipperImageModel flipperImageModel = dataSnapshot1.getValue(FlipperImageModel.class);

                    String url[] = new String[]{
                            flipperImageModel.getImageUri(),
                            flipperImageModel.getImageUri1(),
                            flipperImageModel.getImageUri2()
                    };
//                    url[0] = flipperImageModel.getImageUri();
//                    url[1] = flipperImageModel.getImageUri1();
//                    url[2] = flipperImageModel.getImageUri2();
                    for(int image = 0;image<3;image++){
                        FlipperView view = new FlipperView(getContext());
                        view.setImageUrl(url[image]);
                        flipperLayout.addFlipperView(view);

//            view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
//                @Override
//                public void onFlipperClick(FlipperView flipperView) {
//                    Toast.makeText(getContext(),flipperLayout.getCurrentPagePosition() +1,Toast.LENGTH_SHORT).show();
//                }
//            });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myDialog = new Dialog(getContext());


        myDialog.setContentView(R.layout.profile_dialog);
        close = myDialog.findViewById(R.id.txtclose);
        logout = myDialog.findViewById(R.id.logout_button);
        privacyPolicy = myDialog.findViewById(R.id.privacypolicy);
        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://stackoverflow.com/questions/3004515/sending-an-intent-to-browser-to-open-specific-url"));
                startActivity(intent);
            }
        });



        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        UserName = myDialog.findViewById(R.id.user_name);
        UserEmail = myDialog.findViewById(R.id.user_email);
        UserProfileImage = myDialog.findViewById(R.id.profileImage);


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            String personName = acct.getDisplayName();
            UserName.setText(personName);
            String personEmail = acct.getEmail();
            UserEmail.setText(personEmail);
            Uri personPhoto = acct.getPhotoUrl();
            if(personPhoto!=null) {
                Glide.with(getContext()).load(acct.getPhotoUrl().toString()).into(UserProfileImage);
            }
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent i = new Intent(getActivity(), GeneralLoginActivity.class);
                startActivity(i);
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));








    }

    @Override
    public void onStart() {
        super.onStart();



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                competitionsList = new ArrayList<>();

                for(DataSnapshot postSnap: dataSnapshot.getChildren()){

                    CompetitionModel postModel = postSnap.getValue(CompetitionModel.class);
                    competitionsList.add(postModel);

                }

                competitionAdapter = new CompetitionAdapter(competitionsList,getActivity());
                recyclerView1.setAdapter(competitionAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                winnersList = new ArrayList<>();

                for(DataSnapshot postSnap: dataSnapshot.getChildren()){

                    WinnerModel postModel1 = postSnap.getValue(WinnerModel.class);
                    winnersList.add(postModel1);

                }

                winnerAdapter = new WinnerAdapter(winnersList,getActivity());
                recyclerView2.setAdapter(winnerAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//        DataBase();
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        /// Competition List
//
//        competitionsList = new ArrayList<>();
//
//        competitionsList.add(new CompetitionModel("comp 1",R.drawable.image_1));
//        competitionsList.add(new CompetitionModel("comp 1",R.drawable.articleitemimage));
//        competitionsList.add(new CompetitionModel("comp 1",R.drawable.girlimage1));
//
//        // Winner List
//
//        winnersList = new ArrayList<>();
//
//        winnersList.add(new WinnerModel("Ganni",R.drawable.girlimage1));
//        winnersList.add(new WinnerModel("Ganesh",R.drawable.image_2));
//        winnersList.add(new WinnerModel("Ganesh",R.drawable.girlimage1));
//        winnersList.add(new WinnerModel("Ganesh",R.drawable.articleitemimage));
//
//    }
}

