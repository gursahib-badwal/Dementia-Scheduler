package com.example.dementiademo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dementiademo.databinding.ActivityEmergencyBinding;
import com.example.dementiademo.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.w3c.dom.Text;

public class emergency extends AppCompatActivity {

//    BottomNavigationView bottomNavigationView;
//    HomeFragment homeFragment = new HomeFragment();
//    SettingFragment settingFragment = new SettingFragment();
//    ProfileFragment profileFragment = new ProfileFragment();

    String PersonalName, BloodType, Allergies, Medications, AdressS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        Button personalInformationSettingBtn = (Button) findViewById(R.id.personalInformationSetting);
//        Button backBtn = (Button) findViewById(R.id.emergencyCallBtn);
        TextView NameTextView = (TextView) findViewById(R.id.NameTextView);
        TextView BloodTypeTextView = (TextView) findViewById(R.id.bloodTypeTextView);
        TextView AllergiesTextView = (TextView) findViewById(R.id.allergiesTextView);
        TextView MedicationsTextView = (TextView) findViewById(R.id.medicationTextView);
        TextView AdressTextView = (TextView) findViewById(R.id.addressTextView);
//        bottomNavigationView = findViewById(R.id.bottom_navigation);    // set bottom Navigation

//        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();   //set origin fragement is homeFragment

//        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.home:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();      //Home
//                        return true;
//                    case R.id.profile:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment).commit();    //profileFragment
//                        return true;
//                    case R.id.setting:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container,settingFragment).commit();    //setting Fragment
//                        return true;
//                }   //set Navigation selected
//
//                return false;
//            }
//        });
        Intent intent = getIntent();
        PersonalName = intent.getStringExtra("EXTRA_NAME");
        BloodType = intent.getStringExtra("EXTRA_BLOODTYPE");
        Allergies = intent.getStringExtra("EXTRA_ALLERGIES");
        Medications = intent.getStringExtra("EXTRA_MEDICATIONS");
        AdressS = intent.getStringExtra("EXTRA_ADRESS");

        NameTextView.setText(PersonalName);
        BloodTypeTextView.setText(BloodType);
        AllergiesTextView.setText(Allergies);
        MedicationsTextView.setText(Medications);
        AdressTextView.setText(AdressS);

//        NameTextView.setText(PersonalName);
        personalInformationSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(emergency.this, EmergencyInformationSetting.class));
            }
        });
//        backBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(emergency.this, DementiaPageActivity.class));
//            }
//        });
//
    }

}
