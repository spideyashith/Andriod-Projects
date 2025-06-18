package com.example.sharedpreference;

import static android.view.Gravity.apply;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    Button btnDark, btnLight;
    static int themeId = android.R.style.ThemeOverlay_Material_Light;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        preferences = getSharedPreferences("mypreference", MODE_PRIVATE);
        editor = preferences.edit();

        if(preferences.getBoolean("theme",false)){
            setTheme(android.R.style.ThemeOverlay_Material_Dark);
        } else {
            setTheme(android.R.style.ThemeOverlay_Material_Light);
        }

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);
    }

    public  void findTheme(View view){
        if(R.id.btn_Dark == view.getId()){
            editor.putBoolean("theme",true).apply();
            recreate();
        } else if(R.id.btn_Light == view.getId()){
            editor.putBoolean("theme",false).apply();
            recreate();
        } else if(R.id.btnSecond == view.getId()){
            Intent i = new Intent(this, Second.class);
            startActivity(i);
        }
    }
}