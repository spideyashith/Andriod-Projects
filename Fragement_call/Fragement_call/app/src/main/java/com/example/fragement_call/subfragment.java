package com.example.fragement_call;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class subfragment extends Fragment {

    public subfragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        double first = bundle.getDouble("FNO");
        double second = bundle.getDouble("SNO");
        double sum = first - second;

        View v = inflater.inflate(R.layout.fragment_subfragment,
                container,false);
        TextView result = v.findViewById(R.id.txtaddresult);
        result.setText(String.format("%.2f", sum));

        return v;
    }
}