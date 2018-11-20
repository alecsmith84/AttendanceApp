package com.example.attendanceapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class realLessonActivity extends AppCompatActivity {


    ImageView kermitImage;
    Uri uri;
    Intent intent;



    private View.OnTouchListener mTouchListener=new View.OnTouchListener(){
        @Override

        public boolean onTouch(View v, MotionEvent event) {

            Dialog builder = new Dialog(realLessonActivity.this);
            builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
            builder.getWindow().setBackgroundDrawable(
                    new ColorDrawable(android.graphics.Color.TRANSPARENT));
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    //nothing;
                }
            });

            int a = v.getId();
            if(R.id.kermitImage == a)
            {
                uri = Uri.parse("android.resource://com.example.attendanceapp/"+R.raw.heaven);    //path of image
            }
            else if (R.id.dogVideoView == a){
                uri = Uri.parse("android.resource://com.example.attendanceapp/"+R.raw.doggo);
//                final Intent startFullBoy = new Intent(this, DoggoFullscreenActivity.class);

//                final Intent intent = new Intent(this, MediaActivity.class);
                startActivity(intent);
            }

            ImageView imageView = new ImageView(realLessonActivity.this);
            imageView.setImageURI(uri);                //set the image in dialog popup
            //below code fullfil the requirement of xml layout file for dialoge popup

            builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            builder.show();
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = new Intent(this, DoggoFullscreenActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_lesson);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        VideoView coolVideo = (VideoView) findViewById(R.id.dogVideoView);
        kermitImage = (ImageView) findViewById(R.id.kermitImage);

        kermitImage.setOnTouchListener(mTouchListener);
        coolVideo.setOnTouchListener(mTouchListener);


        String path = "android.resource://com.example.attendanceapp/" + R.raw.doggo;
        Uri uri = Uri.parse(path);
        coolVideo.setVideoURI(uri);
        coolVideo.requestFocus();
        coolVideo.start();
        //
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
