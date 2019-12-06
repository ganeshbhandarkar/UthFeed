package com.ganeshbhandarkar.uthfeed.Compete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.ganeshbhandarkar.uthfeed.Adapters.MoreWinnersAdapter;
import com.ganeshbhandarkar.uthfeed.Models.MoreWinnersModel;
import com.ganeshbhandarkar.uthfeed.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MoreWinnersActivity extends AppCompatActivity {

    private static final String TAG = "MoreWinnersActivity";
    private static final int NUM_COLUMN = 2;
    RecyclerView recyclerView;
    MoreWinnersAdapter moreWinnersAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    List<MoreWinnersModel> moreWinnersModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_winners);

        recyclerView = findViewById(R.id.recyclerViewMoreWinners);

        DataBase();


        staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMN,LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(staggeredGridLayoutManager);

    }

    private void DataBase(){

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("MoreWinners_Posts");

        moreWinnersModelList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                for(DataSnapshot articleSnap: dataSnapshot.getChildren()){

                    MoreWinnersModel moreWinnersModel = articleSnap.getValue(MoreWinnersModel.class);
                    moreWinnersModelList.add(moreWinnersModel);

                }

                moreWinnersAdapter = new MoreWinnersAdapter(moreWinnersModelList,MoreWinnersActivity.this);
                recyclerView.setAdapter(moreWinnersAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
