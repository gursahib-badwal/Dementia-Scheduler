package com.example.dementiademo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmergencyInformationSetting extends AppCompatActivity {
    String PersonalName, BloodType, Allergies, Medications, AdressS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_information_setting);
        Button personalBackBtn = (Button) findViewById(R.id.personalBack);
        Button personalSaveBtn = (Button) findViewById(R.id.personalSave);
        EditText NameEditText = (EditText) findViewById(R.id.NameEditText);
        EditText BloodEdittext = (EditText) findViewById(R.id.bloodTypeEditText);
        EditText AllergiesEdittext = (EditText) findViewById(R.id.allergiesEditText);
        EditText MedicationsEdittext = (EditText) findViewById(R.id.medicationEditText);
        EditText Adress = (EditText) findViewById(R.id.adressEditText);

        personalBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(EmergencyInformationSetting.this, emergency.class));
            }
        });

        personalSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmergencyInformationSetting.this, emergency.class);
                PersonalName = NameEditText.getText().toString();
                BloodType = BloodEdittext.getText().toString();
                Allergies = AllergiesEdittext.getText().toString();
                Medications = MedicationsEdittext.getText().toString();
                AdressS = Adress.getText().toString();
                intent.putExtra("EXTRA_NAME", PersonalName);
                intent.putExtra("EXTRA_BLOODTYPE", BloodType);
                intent.putExtra("EXTRA_ALLERGIES", Allergies);
                intent.putExtra("EXTRA_MEDICATIONS", Medications);
                intent.putExtra("EXTRA_ADRESS", AdressS);
                startActivity(intent);
//                startActivity(new Intent(EmergencyInformationSetting.this,emergency.class));
            }
        });
    }
}