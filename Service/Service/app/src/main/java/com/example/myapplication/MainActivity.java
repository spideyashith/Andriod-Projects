package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;

import com.example.myapplication.Service.MyMusicService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }

        // Method to handle button clicks (must be public void with View parameter)
        public void handleButton(View v) {
            Intent intent = new Intent(this, MyMusicService.class);

            if (v.getId() == R.id.btnPlay) {
                Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
                startService(intent);
            }
            else if (v.getId() == R.id.btnStop) {
                Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
                stopService(intent);

            }

        }
    }