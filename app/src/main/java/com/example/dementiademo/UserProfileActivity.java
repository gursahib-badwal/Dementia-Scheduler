package com.example.dementiademo;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.dementiademo.databinding.ActivityUserprofileBinding;

import org.w3c.dom.Text;

public class UserProfileActivity extends AppCompatActivity {

    private ActivityUserprofileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        String currentUser = bundle.getString("currentUser");

        binding = ActivityUserprofileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //pop user info
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                String res = new RemoteUserDBHelper().queryUserByUsername(currentUser);
                MyUser myuser = MyUser.getUserByJson(res);
                System.out.println(myuser);
                return myuser;
            }

            @Override
            protected void onPostExecute(Object o) {
                MyUser myUser = (MyUser)o;
                Toast.makeText(UserProfileActivity.this, "Rect Successful", Toast.LENGTH_SHORT).show();   // Show Text


                binding.uuidShow.setText(myUser.uuid);
                binding.userNameShow.setText(myUser.username);
                binding.userTypeShow.setText(myUser.user_type);
                binding.firstNameShow.setText(myUser.first_name);
                binding.lastNameShow.setText(myUser.last_name);
                binding.genderShow.setText(myUser.gender);
                binding.descriptionShow.setText(myUser.description);

            }
        }.execute();
    }
}