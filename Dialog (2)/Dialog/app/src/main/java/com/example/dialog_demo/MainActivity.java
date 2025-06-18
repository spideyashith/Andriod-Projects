package com.example.dialog_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText datetxt, timetxt;
    Button aadbtn;
    Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datetxt = findViewById(R.id.txtDate);
        timetxt = findViewById(R.id.txtTime);
        aadbtn = findViewById(R.id.btnAdd);
        cal = Calendar.getInstance();

        datetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayofMonth) {
                                cal.set(Calendar.DAY_OF_MONTH, dayofMonth);
                                cal.set(Calendar.MONTH, month);
                                cal.set(Calendar.YEAR, year);
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                datetxt.setText(dateFormat.format(cal.getTime()));
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        timetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourofDay, int minute) {
                                cal.set(Calendar.HOUR_OF_DAY, hourofDay);
                                cal.set(Calendar.MINUTE,minute);
                                SimpleDateFormat timeformate = new SimpleDateFormat("hh : mm a");
                                timetxt.setText(timeformate.format(cal.getTime()));
                            }
                        }, hour, minute, true);
                timePickerDialog.show();
            }
        });

        aadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AlarmReceiver.class);
                PendingIntent p1 = PendingIntent.getBroadcast(MainActivity.this,
                        123, i,PendingIntent.FLAG_MUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC, cal.getTimeInMillis(),p1);
                Toast.makeText(MainActivity.this, "The date and time we choose is :"
                        +cal.getTime().toString(), Toast.LENGTH_LONG).show();


            }
        });
    }
}