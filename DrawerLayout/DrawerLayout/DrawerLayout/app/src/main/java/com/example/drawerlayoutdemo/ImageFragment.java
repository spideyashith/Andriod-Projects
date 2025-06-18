package com.example.drawerlayoutdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;


public class ImageFragment extends Fragment implements View.OnClickListener{
    private static final int PIC_IMAGE = 100;
    private static final int TAKE_IMAGE = 101;

    public ImageFragment() {
        // Required empty public constructor
    }

    ImageView profileimg;
    Button browsebtn, takebtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_image, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getContext().checkSelfPermission(android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.CAMERA},123);
            }
        }
        profileimg = v.findViewById(R.id.inPreFilePic);
        browsebtn = v.findViewById(R.id.btnBrowseView);
        takebtn = v.findViewById(R.id.btnTakePicture);
        browsebtn.setOnClickListener(this);
        takebtn.setOnClickListener(this);
        return v;

    }

    @Override
    public void onClick(View v) {
      if (v.getId() == R.id.btnBrowseView){
          Intent browseIntent = new Intent();
          browseIntent.setType("image/*");
          browseIntent.setAction(Intent.ACTION_GET_CONTENT);
          startActivityForResult(browseIntent, PIC_IMAGE);
        }
      else if(v.getId() == R.id.btnTakePicture){
          Intent camerIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
          startActivityForResult(camerIntent, TAKE_IMAGE);
      }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            Bitmap bmp = null;
            if(requestCode == PIC_IMAGE) {
                try {
                    bmp = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                else if( requestCode == TAKE_IMAGE){
                bmp = (Bitmap) data.getExtras().get("data");
                }
            profileimg.setImageBitmap(bmp);
            }

        }

}