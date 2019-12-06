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
import com.ganeshbhandarkar.uthfeed.Articles.ArticleFragment;
import com.ganeshbhandarkar.uthfeed.MainThreadActivities.GeneralLoginActivity;
import com.ganeshbhandarkar.uthfeed.R;
import com.ganeshbhandarkar.uthfeed.Videos.VideoFragment;
import com.ganeshbhandarkar.uthfeed.ViewPagerAdapter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class MediaFragment extends Fragment {

    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView profiledialog;

    Dialog myDialog;
    FirebaseAuth mAuth;
    Button close;
    Button logout,privacyPolicy;
    TextView UserName,UserEmail;
    CircleImageView UserProfileImage,profileImage;

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_media,container,false);
        init();

        return view;

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


    private void init() {

        appBarLayout = view.findViewById(R.id.app_bar_media);
        tabLayout = view.findViewById(R.id.tabbar_media);
        viewPager = view.findViewById(R.id.viewPager_media);
        profiledialog = view.findViewById(R.id.profile_dialog);
        profileImage = view.findViewById(R.id.profileImage);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.AddFragment(new VideoFragment(),"Videos and Audios");
        viewPagerAdapter.AddFragment(new ArticleFragment(),"Articles");
        viewPager.setAdapter(viewPagerAdapter);
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
}
