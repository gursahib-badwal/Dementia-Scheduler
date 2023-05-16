package com.example.dementiademo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dementiademo.databinding.ActivityCareTakerPageBinding;
import com.example.dementiademo.databinding.ActivitySecondBinding;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CareTakerPageActivity extends AppCompatActivity {

    private ActivityCareTakerPageBinding binding;

    int hour, minute;
    String temp;


    public static String URL = "williamoverflow.com";
    java.sql.Timestamp postEventTime = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCareTakerPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        // Get event (fetch event when activity swaps)
        new AsyncTask(){
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    List<MyEvent> r =  RemoteEventDBHelper.queryEventsByToUsername("a2037");

                    System.out.println(r.get(0).event_time);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            protected void onPostExecute(Object o) {

            }

        }.execute();


        binding.CareTakerBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(CareTakerPageActivity.this, secondActivity.class));
            }
        });     //Back Button

        binding.CameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent();
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(intent);
                }catch  (Exception e)
                {
                    e.printStackTrace();
                }


            }
        }); // Use Android Camera

        binding.photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        }); // Use Andriod Gallery

        //Send event (sendmessage to server)
        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyEvent myEvent = new MyEvent();
                myEvent.title = "message";
                myEvent.description = binding.SendText.getText().toString();
                myEvent.event_type = 11;        //TODO: TEMP mess up

                if(postEventTime == null)
                    myEvent.event_time = new Timestamp(System.currentTimeMillis());
                else
                    myEvent.event_time = postEventTime;

                if(RemoteUserDBHelper.currentUser == null)
                    myEvent.from_user = "admin";
                else
                    myEvent.from_user = RemoteUserDBHelper.currentUser.username;
                myEvent.to_user = "a2037";      //TODO: TEMP mess up

                new AsyncTask(){
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        try {
                            RemoteEventDBHelper.postEvent(myEvent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return false;
                    }

                    @Override
                    protected void onPostExecute(Object o) {
                        Toast toast = Toast.makeText(getApplicationContext(),"Message Send!", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }.execute();








//                Toast toast = Toast.makeText(getApplicationContext(),"Message Send", Toast.LENGTH_SHORT);
//                toast.show();
//                Intent intent = new Intent(CareTakerPageActivity.this, DementiaPageActivity.class);
//                temp = binding.SendText.getText().toString();
//                intent.putExtra("EXTRA_TEMP", temp);
            }
        });

    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;

                Calendar c1 = Calendar.getInstance();
                c1.set(Calendar.HOUR, hour);
                c1.set(Calendar.MINUTE, minute);
                postEventTime = new Timestamp(c1.getTimeInMillis());

                binding.timeSelect.setText(String.format(Locale.getDefault(), "%02d:%02d (Today)", hour,minute));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener,hour,minute, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == RESULT_OK&&data != null){
//            Uri selectedImage= data.getData();
//            ImageView imageView = findViewById(R.id.imageView);
//            imageView.setImageURL(selectedImage);
//
//        }
//    }
}