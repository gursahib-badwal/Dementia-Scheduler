package com.example.dementiademo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.TextSwitcher;
import android.widget.TextView;

public class DementiaPageActivity extends AppCompatActivity {
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dementia_page);


        Button dementiaBackBtn = (Button) findViewById(R.id.DementiaBackBtn);
        Button EmergencyBtn = (Button) findViewById(R.id.emergency);
        TextView DementiaTextView = (TextView) findViewById(R.id.textSwit);
        dementiaBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DementiaPageActivity.this, secondActivity.class));
            }
        });

        EmergencyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DementiaPageActivity.this, emergency.class));
            }
        });
        Intent intent = getIntent();
        message = intent.getStringExtra("EXTRA_TEMP");
        DementiaTextView.setText(message);


    }

    public void BtnSetEmergency_onClick(View view) {
        String number = "110";  //it should be 911
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel: " + number));
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        startActivity(intent);
    }
}