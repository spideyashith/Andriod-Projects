package com.example.gps_demo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    LocationManager manager;
    LocationListener listener;
    HashMap<String, String> hashMapdata;
    ArrayList<HashMap<String,String>> datalist;
    ListView gpslistview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        manager = (LocationManager) getSystemService(LOCALE_SERVICE);
        datalist = new ArrayList<HashMap<String,String>>();
        gpslistview = findViewById(R.id.lvGPS);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                updateMyLocation(location);
            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                manager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,0,0,listener);
                Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                updateMyLocation(lastLocation);
            }else {
                requestPermissions(new String[]
                        {Manifest.permission.ACCESS_FINE_LOCATION},123);

            }
        }


    }
}