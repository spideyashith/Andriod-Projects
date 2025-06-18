package com.example.drawerlayoutdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Toast;

import java.io.IOException;

public class AudioFragment extends Fragment implements View.OnClickListener {

    private static final int PICK_AUDIO = 200;
    private static final int REQUEST_PERMISSIONS = 123;

    TextView statustxt;
    Button choosebtn, recordbtn;
    ToggleButton toggleButton;
    MediaPlayer player;
    MediaRecorder recorder;
    String storepath;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_audio, container, false);

        statustxt = v.findViewById(R.id.txtStatus);
        choosebtn = v.findViewById(R.id.btnAudioChoose);
        recordbtn = v.findViewById(R.id.btnRecordAudio);
        toggleButton = v.findViewById(R.id.toggleButton);

        choosebtn.setOnClickListener(this);
        recordbtn.setOnClickListener(this);
        toggleButton.setOnClickListener(this);

        storepath = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS).getPath() + "/MyVoice.3gp";

        requestAllPermissions(); // Request permissions on startup

        return v;
    }

    private void requestAllPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                }, REQUEST_PERMISSIONS);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAudioChoose) {
            Intent chooseIntent = new Intent();
            chooseIntent.setType("audio/*");
            chooseIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(chooseIntent, PICK_AUDIO);
        } else if (v.getId() == R.id.btnRecordAudio) {
            statustxt.setText("The recording will be stored at " + storepath);

            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Microphone permission not granted.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (recordbtn.getText().equals("Record Audio")) {
                recorder = new MediaRecorder();
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                recorder.setOutputFile(storepath);

                try {
                    recorder.prepare();
                    recorder.start();
                    statustxt.setText("Recording started...");
                    recordbtn.setText("Stop Recording");
                } catch (IOException e) {
                    e.printStackTrace();
                    statustxt.setText("Recording failed.");
                }
            } else {
                try {
                    recorder.stop();
                    recorder.release();
                    recorder = null;

                    player = MediaPlayer.create(getContext(), Uri.parse(storepath));
                    player.start();

                    statustxt.setText("Recording stopped. Playing audio...");
                    recordbtn.setText("Record Audio");
                } catch (Exception e) {
                    e.printStackTrace();
                    statustxt.setText("Error stopping recorder.");
                }
            }
        } else if (v.getId() == R.id.toggleButton) {
            if (player != null && player.isPlaying()) {
                player.pause();
                statustxt.setText("Paused");
            } else if (player != null) {
                player.start();
                statustxt.setText("Resumed");
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == PICK_AUDIO) {
            Uri audioUri = data.getData();
            player = MediaPlayer.create(getContext(), audioUri);
            player.start();
            statustxt.setText("Media is playing from: " + audioUri.toString());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(), "Please grant all permissions", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            Toast.makeText(getContext(), "Permissions granted", Toast.LENGTH_SHORT).show();
        }
    }
}
