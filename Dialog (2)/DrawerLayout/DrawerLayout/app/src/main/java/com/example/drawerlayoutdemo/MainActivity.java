package com.example.drawerlayoutdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_image){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ImageFragment()).commit();
        }
        if (item.getItemId() == R.id.nav_audio){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AudioFragment()).commit();
        }
        if(item.getItemId() == R.id.nav_video){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new VedioFragment()).commit();
        }
        if(item.getItemId() == R.id.nav_share){
            Intent i = new Intent();
            i.setType("text/*");
            i.setAction(Intent.ACTION_SEND);
            i.putExtra(Intent.EXTRA_TEXT,"This is the data i will be sharing");
            Intent shareIntent = Intent.createChooser(i,"data that is share");
            startActivity(shareIntent);
        }
        return false;
    }
}