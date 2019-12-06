package com.ganeshbhandarkar.uthfeed.Articles;

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

public class ArticleFragment extends Fragment {

    View view;
    List<ArticlePostModel> articleList;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    ArticlePostAdapter articlePostAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_articles,container,false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Articles_Posts");

        init();

        recyclerView = view.findViewById(R.id.recyclerViewArticle);
//        ArticleAdapter articleAdapter = new ArticleAdapter(getContext(),articleModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(articleAdapter);



        return view;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        articleModelList = new ArrayList<>();
//
//        articleModelList.add(new ArticleModel(R.drawable.image_1," kids’ health & fitness, education, secret grandmother healing tips and so much more. ","Parents Group","https://www.parents.com/"));
//        articleModelList.add(new ArticleModel(R.drawable.image_2,"Our Core Team is here to set up a community platform for people to come We all love our kids more than anything else, isn’t it","kids presso","https://www.kidspresso.com/"));
//        articleModelList.add(new ArticleModel(R.drawable.girlimage1,"While parenting teens resemble the .","Ram Arrora",""));
//        articleModelList.add(new ArticleModel(R.drawable.image_1,"While parenting teens , God provides the stability we crave.","Ram Arrora",""));
//        articleModelList.add(new ArticleModel(R.drawable.articleitemimage," of riding a rollercoaster, God provides the stability we crave.","Ram Arrora",""));
//        articleModelList.add(new ArticleModel(R.drawable.image_1,"a rollercoaster, God provides the stability we crave.","Ram Arrora",""));
//        articleModelList.add(new ArticleModel(R.drawable.girlimage1,"While riding a rollercoaster, God provides the stability we crave.","Ram Arrora",""));
//        articleModelList.add(new ArticleModel(R.drawable.articleitemimage,"resemble the helter-skelter throes of riding a rollercoaster, God provides the stability we crave.","Ram Arrora",""));
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

                articleList = new ArrayList<>();

                for (DataSnapshot postSnap : dataSnapshot.getChildren()) {

                    ArticlePostModel articlePostModel = postSnap.getValue(ArticlePostModel.class);
                    articleList.add(articlePostModel);

                }

                articlePostAdapter = new ArticlePostAdapter(articleList,getActivity());
                recyclerView.setAdapter(articlePostAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
