package com.example.dementiademo;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import com.example.dementiademo.databinding.ActivitySecondBinding;
import com.example.dementiademo.databinding.ActivityUserprofileBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.locks.Lock;

public class secondActivity extends AppCompatActivity {


    private ActivitySecondBinding binding;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle bundle = getIntent().getExtras();
//        String currentUser = bundle.getString("currentUser");


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();      // Build google Sign Out button
        gsc = GoogleSignIn.getClient(this, gso);

        String personName = "Admin";      // Person name
        String personEmail = "No email address";     // Email adress

        //get Sign in account information
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if(acct != null){
            personName = acct.getDisplayName();         //Get Google Name
            personEmail = acct.getEmail();              //Get Google Adress
        }
        if(RemoteUserDBHelper.currentUser != null){
            personName = RemoteUserDBHelper.currentUser.first_name + " " +
                    RemoteUserDBHelper.currentUser.last_name;
            personEmail = RemoteUserDBHelper.currentUser.username;
        }

        binding.nameShow.setText(personName);
        binding.emailShow.setText(personEmail);

        //Dementia Btn clicker
        binding.DementiaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDementiaActivity();
            }
        });

        //CareTaker Btn clicker
        binding.careTakerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.CareTakerBtnLocker.isChecked() == false){
                    Toast.makeText(getApplicationContext(),"This Button is Lock", Toast.LENGTH_SHORT);
                }
                else{
                    navigateToCareTakerActivity();
                }
            }
        });

        //Sign out Btn clicker
        binding.signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

    }

    //Jum to Dementia Page
    void navigateToDementiaActivity(){
        finish();
        startActivity(new Intent(secondActivity.this, DementiaPageActivity.class));

    }

    //Jum to CareTaker Page
    void navigateToCareTakerActivity(){
        finish();
        startActivity(new Intent(secondActivity.this, CareTakerPageActivity.class));
    }


    //Sign out function
    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                startActivity(new Intent(secondActivity.this, MainActivity.class));
            }
        });
    }



}