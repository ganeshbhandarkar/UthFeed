package com.ganeshbhandarkar.uthfeed.MainThreadActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ganeshbhandarkar.uthfeed.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    Dialog myDialog;
    FirebaseAuth mAuth;
    Button close;
    Button logout;
    TextView UserName;
    Button PrivacyPolicy;
    String privacy = "http://stackoverflow.com/questions/3004515/sending-an-intent-to-browser-to-open-specific-url";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        myDialog = new Dialog(this);

        UserName = findViewById(R.id.user_name);
        PrivacyPolicy = findViewById(R.id.privacypolicy);
        myDialog.setContentView(R.layout.profile_dialog);
        close = myDialog.findViewById(R.id.txtclose);
        logout = myDialog.findViewById(R.id.logout_button);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                Intent i = new Intent(ProfileActivity.this,MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

        PrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "hhaaa", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://stackoverflow.com/questions/3004515/sending-an-intent-to-browser-to-open-specific-url"));
                startActivity(intent);
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this,"Hello",Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                Intent i = new Intent(ProfileActivity.this,GeneralLoginActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

    }

    public void openUrl(View v){

    }





}
