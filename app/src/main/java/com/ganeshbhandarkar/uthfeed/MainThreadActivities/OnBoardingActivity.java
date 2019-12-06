package com.ganeshbhandarkar.uthfeed.MainThreadActivities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.chyrta.onboarder.OnboarderActivity;
import com.chyrta.onboarder.OnboarderPage;
import com.ganeshbhandarkar.uthfeed.R;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends OnboarderActivity {

    List<OnboarderPage> onboarderPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onboarderPages = new ArrayList<OnboarderPage>();

        // Create your first page
        OnboarderPage onboarderPage1 = new OnboarderPage("UthFeed", "The Ultimate Feed", R.drawable.onboard_1);
        OnboarderPage onboarderPage2 = new OnboarderPage("Competitions", "Take Part In Monthly Competitions, From Technical to Fashion, Cultural and More", R.drawable.onboard_2);
        OnboarderPage onboarderPage3 = new OnboarderPage("Sharing", "Share The Thing's On Mind", R.drawable.onboard_3);

        // You can define title and description colors (by default white)
        onboarderPage1.setTitleColor(R.color.colorPrimaryDark);
        onboarderPage1.setDescriptionColor(R.color.colorPrimaryDark);
        onboarderPage2.setTitleColor(R.color.colorPrimaryDark);
        onboarderPage2.setDescriptionColor(R.color.colorPrimaryDark);
        onboarderPage3.setTitleColor(R.color.title);
        onboarderPage3.setDescriptionColor(R.color.title);



        // Don't forget to set background color for your page
        onboarderPage1.setBackgroundColor(R.color.onBoardOne);
        onboarderPage2.setBackgroundColor(R.color.onBoardTwo);
        onboarderPage3.setBackgroundColor(R.color.onBoardThree);

        // Add your pages to the list
        onboarderPages.add(onboarderPage1);
        onboarderPages.add(onboarderPage2);
        onboarderPages.add(onboarderPage3);

        // And pass your pages to 'setOnboardPagesReady' method
        setOnboardPagesReady(onboarderPages);

        // And pass your pages to 'setOnboardPagesReady' method
        setOnboardPagesReady(onboarderPages);



        shouldUseFloatingActionButton(true);

        setDividerVisibility(View.GONE);
        setDividerColor(Color.TRANSPARENT);
        setProgressBarVisibility(true);
        setProgressBarIndeterminate(true);
        setProgressBarIndeterminateVisibility(true);

//        shouldDarkenButtonsLayout(true);


    }

    @Override
    public void onFinishButtonPressed() {
        Intent i = new Intent(OnBoardingActivity.this,MainActivity.class);
        startActivity(i);
    }
}
