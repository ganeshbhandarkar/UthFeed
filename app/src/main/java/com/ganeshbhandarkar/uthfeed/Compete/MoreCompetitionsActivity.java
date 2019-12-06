package com.ganeshbhandarkar.uthfeed.Compete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ganeshbhandarkar.uthfeed.Adapters.MoreCompetitionAdapter;
import com.ganeshbhandarkar.uthfeed.Models.MoreCompetitionModel;
import com.ganeshbhandarkar.uthfeed.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MoreCompetitionsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MoreCompetitionAdapter moreCompetitionAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    LinearLayoutManager linearLayoutManager;
    List<MoreCompetitionModel> moreCompetitionModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_competitions);

        recyclerView = findViewById(R.id.recyclerViewMoreCompetition);

        DataBase();


        linearLayoutManager = new LinearLayoutManager(MoreCompetitionsActivity.this);


        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);




    }

    private void DataBase(){

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("MoreCompetition_Posts");

        moreCompetitionModelList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                for(DataSnapshot articleSnap: dataSnapshot.getChildren()){

                    MoreCompetitionModel moreCompetitionModel = articleSnap.getValue(MoreCompetitionModel.class);
                    moreCompetitionModelList.add(moreCompetitionModel);

                }

                moreCompetitionAdapter = new MoreCompetitionAdapter(moreCompetitionModelList,MoreCompetitionsActivity.this);
                recyclerView.setAdapter(moreCompetitionAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
