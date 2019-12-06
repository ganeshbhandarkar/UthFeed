package com.ganeshbhandarkar.uthfeed.MainThreadActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ganeshbhandarkar.uthfeed.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private TextInputLayout Email_layout,Password_layout,ConfirmPassword_layout,name_layout;
    private TextInputEditText Email,Password,ConfirmPassword,Name;
    private Button BackButton;
    private RelativeLayout CreateAccountButton;
    private ProgressDialog loadingBar;
    private String email,password,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        BackButton = findViewById(R.id.back_button);
        mAuth = FirebaseAuth.getInstance();

        CreateAccountButton = findViewById(R.id.userCreateAccount);
        name_layout = findViewById(R.id.name_input_layout);
        Email_layout = findViewById(R.id.email_input_layout);
        Password_layout = findViewById(R.id.password_input_layout);
        ConfirmPassword_layout = findViewById(R.id.confirm_password_input_layout);
        Name = findViewById(R.id.username);
        Email = findViewById(R.id.useremail);
        Password = findViewById(R.id.userpassword);
        ConfirmPassword = findViewById(R.id.userconfirmpassword);
        loadingBar = new ProgressDialog(this);




        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this,GeneralLoginActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_up,R.anim.fade_out);
            }
        });

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToMainActivity();
            }
        });
    }

    private void sendToMainActivity() {

        mAuth = FirebaseAuth.getInstance();

        name = Name.getText().toString();
        email = Email.getText().toString();
        password = Password.getText().toString();
        String confirmPassword = ConfirmPassword.getText().toString();


        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Write your Email", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please Write your Name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Write your Password", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(confirmPassword)){
            Toast.makeText(this, "Please Confirm your Password", Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(confirmPassword)){
            Toast.makeText(this, "Your Password don't match with your confirm password", Toast.LENGTH_SHORT).show();
        }
        else{

            loadingBar.setTitle("Creating new Account");
            loadingBar.setMessage("Please Wait, While we are Creating Your new Account");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this, "You are Authenticated Successfully ", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
//                                SendUserInfo();
                                Intent signUpIntent = new Intent(SignUpActivity.this,LoginActivity.class);
                                signUpIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(signUpIntent);
                                finish();
                            }
                            else{
                                loadingBar.dismiss();
                                String message = task.getException().getMessage();
                                Toast.makeText(SignUpActivity.this,"There is some error" + message,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

//    public void SendUserInfo(){
//
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference UserRef = firebaseDatabase.getReference(mAuth.getUid());
//        UserProfile userProfile = new UserProfile(name,email,);
//        UserRef.setValue(userProfile);
//    }
}
