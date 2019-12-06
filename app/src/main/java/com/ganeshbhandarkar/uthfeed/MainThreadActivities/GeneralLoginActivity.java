package com.ganeshbhandarkar.uthfeed.MainThreadActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ganeshbhandarkar.uthfeed.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class GeneralLoginActivity extends AppCompatActivity {

    private RelativeLayout mylayout;
    private AnimationDrawable animationDrawable;
    private Button LoginButton,SignUpButton,FaceBookLoginButton,GoogleLoginButton;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private ProgressDialog loadingBar;
    TextView UserName;
    public String Name,Email;
    public String Photo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_login);

        mAuth = FirebaseAuth.getInstance();



        UserName = findViewById(R.id.user_name);

        mylayout = findViewById(R.id.mylayout);

        animationDrawable = (AnimationDrawable) mylayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();


//        LoginButton = findViewById(R.id.login_button);
//        SignUpButton = findViewById(R.id.siqnup_button);
        FaceBookLoginButton = findViewById(R.id.facebook_signIn_button);
        GoogleLoginButton = findViewById(R.id.google_signIn_button);
        loadingBar = new ProgressDialog(this);

        FaceBookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GeneralLoginActivity.this, "Coming Soon.. In new Update...", Toast.LENGTH_SHORT).show();
            }
        });
//        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");

//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
//        if (acct != null) {
//            String userName = acct.getDisplayName();
//            String userEmail = acct.getEmail();
//        }


//        LoginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(GeneralLoginActivity.this,LoginActivity.class);
//                startActivity(i);
//                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
//            }
//        });
//
//        SignUpButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent j = new Intent(GeneralLoginActivity.this,SignUpActivity.class);
//                startActivity(j);
//                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
//            }
//        });


        /// Google SignIn

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);

        GoogleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);
            }
        });





    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately

                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);

        mAuth = FirebaseAuth.getInstance();

        loadingBar.setTitle("Logging In ");
        loadingBar.setMessage("Please wait As You are Entering the World of Innovations");
        loadingBar.setCanceledOnTouchOutside(true);
        loadingBar.show();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            DataBase();
                            SendUserInfo();
                            loadingBar.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent j = new Intent(GeneralLoginActivity.this,MainActivity.class);
                            startActivity(j);
                            overridePendingTransition(R.anim.pull_up_from_buttom,R.anim.fade_out);
                            finish();


                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();

                            String message = task.getException().getMessage();
                            Toast.makeText(GeneralLoginActivity.this,"Error"+ message,Toast.LENGTH_SHORT).show();
                        }


                    }
                });

    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseAuth mAuth = FirebaseAuth.getInstance();
//        FirebaseUser currentUser  = mAuth.getCurrentUser();
//        if (currentUser == null) {
//            SendUserToLoginActivity();
//        }
//        else{
//            CheckUserExistance();
//        }
//    }

//
//    private void CheckUserExistance() {
//
//
//        final String current_user_id = mAuth.getCurrentUser().getUid();
//
//        UserRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if(!dataSnapshot.hasChild(current_user_id)){
////                    SendSetup();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//    }

    private void SendUserInfo() {

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            Name = acct.getDisplayName();
            Email = acct.getEmail();
            Photo = acct.getPhotoUrl().toString();
        }
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference UserRef = firebaseDatabase.getReference("Users").child(mAuth.getCurrentUser().getDisplayName());
        UserProfile userProfile = new UserProfile(Name,Email,Photo);
        UserRef.setValue(userProfile);

        final String user_id = FirebaseAuth.getInstance().getUid();

        Map<String,String> userMap = new HashMap<>();
        userMap.put("name",Name);
        userMap.put("email",Email);

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("users").document(user_id).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(GeneralLoginActivity.this, "Added to database", Toast.LENGTH_SHORT).show();
                }else{
                    String message = task.getException().getMessage();
                    Toast.makeText(GeneralLoginActivity.this, "error occur" + message, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void DataBase(){




    }
}
