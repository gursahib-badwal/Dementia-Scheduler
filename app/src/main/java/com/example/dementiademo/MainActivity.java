package com.example.dementiademo;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


public class MainActivity extends AppCompatActivity {
    GoogleSignInClient gsc;
    private static int RC_SIGN_IN = 100;  //random number to check login information


    // Main activity function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView username = (TextView) findViewById(R.id.username); // get Username textview box
        TextView password = (TextView) findViewById(R.id.password); // get password textview box
//        Button withoutLogInBtn = (Button) findViewById(R.id.withoutlogbutton); // temp button to test

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
                // get Google SignIn information


        gsc = GoogleSignIn.getClient(this, gso);
                // get Google SignIn Client
        Button LogIn = (Button) findViewById(R.id.logInButton);
                // get Reugular SignIn buttton

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
                // Google AutoSignIn
//        navigateToSecondActivity();


        SignInButton signInButton = findViewById(R.id.google_btn);  // get Google SignIn button
        signInButton.setSize(SignInButton.SIZE_STANDARD);           // Set Google SignIn button Size

        // GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        // if (acct != null) {
        //     String personName = acct.getDisplayName();
        //     String personGivenName = acct.getGivenName();
        //     String personFamilyName = acct.getFamilyName();
        //     String personEmail = acct.getEmail();
        //     String personId = acct.getId();
        //     Uri personPhoto = acct.getPhotoUrl();
        // // }
        // if(acct != null){
        //     startActivity = new Intent(MainActivity.this, secondActivity.class);
        // }


        //set regular Sign in Button when Click
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask(){
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        try {
                            Boolean res = new RemoteUserDBHelper().tryLogin(
                                    username.getText().toString(), password.getText().toString());
                            if(res) {   //login succeed, try queue user data
                                RemoteUserDBHelper.queryUserByUsername(username.getText().toString());
                            }
                            return res;
                        } catch (
                                Exception e) {
                            RemoteUserDBHelper.currentUser = null;
                            e.printStackTrace();
                        }
                        return false;
                    }

                    @Override
                    protected void onPostExecute(Object o) {
                        System.out.println(o);
                        if(!(boolean) o)
                            return;
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();   // Show Text
                        Intent intent = new Intent(MainActivity.this, secondActivity.class);
//                        intent.putExtra("currentUser", username.getText().toString());  //not safe, consider using static fields
                        startActivity(intent);                 // jump to new activity
                    }   //TODO: Ji-de zhi-hou gai-hui-lai!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                }.execute();



//                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
//                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();   // Show Text
//                    startActivity(new Intent(MainActivity.this, secondActivity.class));                 // jump to new activity
//                }
//                else{
//                    Toast.makeText(MainActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();         // Show Text
//                }
            }
        });




        // Set Google SignIn button
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

//        withoutLogInBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                startActivity(new Intent(MainActivity.this, secondActivity.class));
//            }
//        });

    }

    // others Functional part

    // Sign In function(Google)
    private void signIn() {
        Intent signInIntent = gsc.getSignInIntent();    
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                task.getResult(ApiException.class);
//                navigateToSecondActivity();
//            } catch (ApiException e) {
//                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//            }
            handleSignInResult(task);

        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            navigateToSecondActivity();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Toast.makeText(getApplicationContext(),"Something wrong", Toast.LENGTH_SHORT);
        }
    }
    void navigateToSecondActivity(){
        finish();
        Intent intent = new Intent(MainActivity.this,secondActivity.class);
        startActivity(intent);
    }

}
