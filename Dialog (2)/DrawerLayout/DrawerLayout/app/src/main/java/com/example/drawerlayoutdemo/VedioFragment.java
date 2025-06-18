package com.example.drawerlayoutdemo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;


public class VedioFragment extends Fragment implements View.OnClickListener{
    private static final int PICK_VIDEO = 300;
    private static final int TAKE_VIDEO = 301;

        public VedioFragment() {
        // Required empty public constructor
    }
    VideoView videoView;
    Button choosebtn, recordbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_vedio, container, false);
        videoView = v.findViewById(R.id.videoView);
        choosebtn = v.findViewById(R.id.btnChooseVideo);
        recordbtn = v.findViewById(R.id.btnRecordVideo);
        choosebtn.setOnClickListener(this);
        recordbtn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnChooseVideo){
            Intent videoIntent = new Intent();
            videoIntent.setType("video/*");
            videoIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(videoIntent, PICK_VIDEO);
        } else if( v.getId() == R.id.btnRecordVideo){
            Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(cameraIntent, TAKE_VIDEO);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            if(requestCode == PICK_VIDEO){
                videoView.setVideoURI(data.getData());
                videoView.start();
            } else if (requestCode == TAKE_VIDEO){
                videoView.setVideoURI(data.getData());
                videoView.start();
            }
        }
    }
}