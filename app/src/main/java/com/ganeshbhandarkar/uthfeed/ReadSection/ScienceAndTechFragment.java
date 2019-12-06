package com.ganeshbhandarkar.uthfeed.ReadSection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ganeshbhandarkar.uthfeed.Adapters.PostAdapter;
import com.ganeshbhandarkar.uthfeed.Models.PostModel;
import com.ganeshbhandarkar.uthfeed.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ScienceAndTechFragment extends Fragment {

    View view;
    List<PostModel> mlist;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    PostAdapter postAdapter;

    public ScienceAndTechFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_science_and_tech, container, false);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Science_And_Tech_Posts");

        recyclerView = view.findViewById(R.id.r_v_sci);
//        AdapterClassActivity adapterClassActivity = new AdapterClassActivity(modellist,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(adapterClassActivity);

        init();
        return view;
    }

    private void init() {


    }


    @Override
    public void onStart() {
        super.onStart();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mlist = new ArrayList<>();

                for (DataSnapshot postSnap : dataSnapshot.getChildren()) {

                    PostModel postModel = postSnap.getValue(PostModel.class);
                    mlist.add(postModel);

                }

                postAdapter = new PostAdapter(getActivity(), mlist);
                recyclerView.setAdapter(postAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
////        modellist = new ArrayList<>();
////        modellist.add(new ModelClassActivity("Trend ","Raj Bilr",R.drawable.articleitemimage));
////        modellist.add(new ModelClassActivity("Trend ","Raj Bilr",R.drawable.articleitemimage));
////        modellist.add(new ModelClassActivity("Trend ","Raj Bilr",R.drawable.articleitemimage));
////        modellist.add(new ModelClassActivity("Trend ","Raj Bilr",R.drawable.articleitemimage));
////        modellist.add(new ModelClassActivity("Trend ","Raj Bilr",R.drawable.articleitemimage));
//    }
//}


/*  View view;
    List<PostModel> mlist;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    RecyclerView recyclerView;

    public TrendingFragment() {
        // Required Empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trending,container,false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Trending_Posts");

        recyclerView = view.findViewById(R.id.r_v_trending);
        PostAdapter adapterClassActivity = new PostAdapter(getContext(),mlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapterClassActivity);
        init();
        return view;
    } */