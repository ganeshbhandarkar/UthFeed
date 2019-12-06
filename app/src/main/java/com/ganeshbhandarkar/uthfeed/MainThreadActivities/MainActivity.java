package com.ganeshbhandarkar.uthfeed.MainThreadActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.ganeshbhandarkar.uthfeed.MainFragments.MediaFragment;
import com.ganeshbhandarkar.uthfeed.MainFragments.NewFeedFragment;
import com.ganeshbhandarkar.uthfeed.MainFragments.ReadFragment;
import com.ganeshbhandarkar.uthfeed.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity  {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseDatabase database;
    private DatabaseReference UserRef;
    private BottomNavigationView bottomNavigationView;
    private TextView UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();
        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");

        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setTitle("New Feed");
        NewFeedFragment fragment= new NewFeedFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content,fragment,"Fragment Name");
        fragmentTransaction.addToBackStack(null).commit();



    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser  = mAuth.getCurrentUser();
        if (currentUser == null) {
            SendUserToLoginActivity();
        }
        else{
            CheckUserExistance();
        }
    }


    private void CheckUserExistance() {


        final String current_user_id = mAuth.getCurrentUser().getUid();

        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChild(current_user_id)){
//                    SendSetup();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void SendUserToLoginActivity() {

        Intent loginIntent = new Intent(MainActivity.this,GeneralLoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        finish();

    }



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_new_feed:


                    //fragment code
                    setTitle("New Feed");
                    NewFeedFragment fragment= new NewFeedFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content,fragment,"Fragment Name");
                    fragmentTransaction.commit();



                    return true;
                case R.id.nav_media:

                    //fragment code


                    setTitle("Media");
                    MediaFragment fragment2= new MediaFragment();
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.content,fragment2,"Fragment Name");
                    fragmentTransaction2.commit();


                    return true;
                case R.id.nav_read:

                    //fragment code
                    setTitle("Read");
                    ReadFragment fragment3= new ReadFragment();
                    FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.content,fragment3,"Fragment Name");
                    fragmentTransaction3.commit();

                    return true;


            }
            return false;
        }
    };


}


