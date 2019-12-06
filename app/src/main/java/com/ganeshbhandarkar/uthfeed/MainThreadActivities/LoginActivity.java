package com.ganeshbhandarkar.uthfeed.MainThreadActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ganeshbhandarkar.uthfeed.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button BackButton,LoginButton;
    private TextView CreateButton;
    private FirebaseAuth mAuth;
    private TextInputLayout login_layout,password_layout;
    private TextInputEditText User_Email_input,User_Password_input;
    private ProgressDialog loadingbar;
    private String email,name,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        BackButton = findViewById(R.id.back_button);
        LoginButton = findViewById(R.id.userLogin);
        CreateButton = findViewById(R.id.userCreateAccount);
        login_layout = findViewById(R.id.email_input_layout);
        password_layout = findViewById(R.id.password_input_layout);
        User_Email_input = findViewById(R.id.useremail);
        User_Password_input = findViewById(R.id.userpassword);
        loadingbar = new ProgressDialog(this);

//        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,GeneralLoginActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_up,R.anim.fade_out);
            }
        });

        CreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(j);
                overridePendingTransition(R.anim.pull_up_from_buttom,R.anim.fade_out);
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToMainActivity();
            }
        });

    }

    private void setToMainActivity() {


        email = User_Email_input.getText().toString();
        password = User_Password_input.getText().toString();

        mAuth = FirebaseAuth.getInstance();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
        }else{

            loadingbar.setTitle("Logging In ");
            loadingbar.setMessage("Please wait while entering the World of Parents");
            loadingbar.setCanceledOnTouchOutside(true);
            loadingbar.show();

            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "You are Logged In Successfully ", Toast.LENGTH_SHORT).show();
                                loadingbar.dismiss();
//                                SendUserInfo();
                                Intent loginIntent = new Intent(LoginActivity.this,MainActivity.class);
                                loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(loginIntent);
                                overridePendingTransition(R.anim.pull_up_from_buttom,R.anim.fade_out);
                                finish();

                            }else{
                                loadingbar.dismiss();
                                String message = task.getException().getMessage();
                                Toast.makeText(LoginActivity.this, "It is not Completed due to"+ message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    }

//    public void SendUserInfo(){
//
//
//
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference UserRef = firebaseDatabase.getReference(mAuth.getUid());
//        UserProfile userProfile = new UserProfile(name,email);
//        UserRef.setValue(userProfile);
//    }
}
