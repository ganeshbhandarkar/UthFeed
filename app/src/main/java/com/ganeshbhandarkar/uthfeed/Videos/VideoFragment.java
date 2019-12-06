package com.ganeshbhandarkar.uthfeed.Videos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ganeshbhandarkar.uthfeed.Articles.ArticlePostAdapter;
import com.ganeshbhandarkar.uthfeed.Articles.ArticlePostModel;
import com.ganeshbhandarkar.uthfeed.R;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment {

    View view;
    List<VideoModel> videoModelList;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    VideoAdapter videoAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_videos,container,false);
        init();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Videos_Posts");

        recyclerView = view.findViewById(R.id.recyclerViewVideo);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;

    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        videoModelList = new ArrayList<>();
//
//        videoModelList.add(new VideoModel("https://www.youtube.com/embed/UIXcKIz_UDA"));
//        videoModelList.add(new VideoModel("https://www.youtube.com/embed/RBUbja6V9Aw"));
//        videoModelList.add(new VideoModel("https://www.youtube.com/embed/9mWdw-09dso"));
//        videoModelList.add(new VideoModel("https://www.youtube.com/watch?v=9mWdw-09dso"));
//        videoModelList.add(new VideoModel("https://www.youtube.com/watch?v=9mWdw-09dso"));
//
//    }

    private void init() {


    }


    @Override
    public void onStart() {
        super.onStart();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                videoModelList = new ArrayList<>();

                for (DataSnapshot videoSnap : dataSnapshot.getChildren()) {

                    VideoModel videoModel = videoSnap.getValue(VideoModel.class);
                    videoModelList.add(videoModel);

                }

                videoAdapter = new VideoAdapter(getActivity(),videoModelList);
                recyclerView.setAdapter(videoAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//
//    }
}
