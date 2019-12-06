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
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.ganeshbhandarkar.uthfeed.MainThreadActivities.GeneralLoginActivity;
import com.ganeshbhandarkar.uthfeed.R;
import com.ganeshbhandarkar.uthfeed.ReadSection.EntertainmentFragment;
import com.ganeshbhandarkar.uthfeed.ReadSection.NewsAndPoliticsFragment;
import com.ganeshbhandarkar.uthfeed.ReadSection.PsychologyAndMentalHealthFragment;
import com.ganeshbhandarkar.uthfeed.ReadSection.ScienceAndTechFragment;
import com.ganeshbhandarkar.uthfeed.ReadSection.TrendingFragment;
import com.ganeshbhandarkar.uthfeed.ViewPagerAdapter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ReadFragment extends Fragment {

    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView profiledialog;

    Dialog myDialog;
    FirebaseAuth mAuth;
    Button close;
    Button logout,privacyPolicy;
    TextView UserName,UserEmail;
    ImageView UserProfileImage;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_read,container,false);
        init();
//        getDetails();
        return view;
    }




    private void init() {

        appBarLayout = view.findViewById(R.id.app_bar_read);
        tabLayout = view.findViewById(R.id.tabbar_read);
        viewPager = view.findViewById(R.id.viewPager_read);
        profiledialog = view.findViewById(R.id.profile_dialog);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.AddFragment(new TrendingFragment(),"Trending");
        adapter.AddFragment(new ScienceAndTechFragment(),"Science and Tech");
        adapter.AddFragment(new EntertainmentFragment(),"Entertainment");
        adapter.AddFragment(new PsychologyAndMentalHealthFragment(),"Health");
        adapter.AddFragment(new NewsAndPoliticsFragment(),"News and Politics");


//        adapter.AddFragment(new NewsAndPoliticsFragment(),"Articles");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        profiledialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.show();
            }
        });


        //profile Dialog

        mAuth = FirebaseAuth.getInstance();


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

}
