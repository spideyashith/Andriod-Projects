package com.example.fragement_call;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText firsttxt,secondtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firsttxt = findViewById(R.id.txtFirst);
        secondtxt = findViewById(R.id.txtSecond);


    }


   public void findresult(View v){
        double firstNo = Double.parseDouble(firsttxt.getText().toString());
        double secondNo = Double.parseDouble(secondtxt.getText().toString());

        Bundle bundle = new Bundle();
        bundle.putDouble("FNO",firstNo);
        bundle.putDouble("SNO",secondNo);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();


        if(v.getId() == R.id.btnAdd){
            addfragment addfrag = new addfragment();
            addfrag.setArguments(bundle);

            transaction.replace(R.id.frAddContainer,
                    addfrag,"ADDFRAG");

       }else if(v.getId() == R.id.btnSub){



            subfragment subfrag = new subfragment();
            subfrag.setArguments(bundle);
            transaction.replace(R.id.frSubContainer,
                    subfrag,"SUBFRAG");

       }else if(v.getId() == R.id.btnMul){




           mulfragment mulfrag = new mulfragment();
           mulfrag.setArguments(bundle);
           transaction.replace(R.id.frMulContainer,
                   mulfrag,"MULFRAG");

       }else if (v.getId() == R.id.btnDiv) {



           divfragment divfrag = new divfragment();
           divfrag.setArguments(bundle);
           transaction.replace(R.id.frDivContainer,
                   divfrag,"DIVFRAG");

       }

        transaction.commit();

   }
}