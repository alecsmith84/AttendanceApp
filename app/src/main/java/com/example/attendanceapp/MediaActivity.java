package com.example.attendanceapp;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class MediaActivity extends AppCompatActivity {


//    Button btnTakePhoto;
    ImageView imageView;
    String pathToFile;
    TextView textbox = null;
    String resolvedCounterValue = "1";
    private static final String FILE_NAME = "example.txt";
    private static final String COUNTER_FILE_NAME = "counterexample.txt";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        textbox = findViewById(R.id.lessonSummary);

        resolvedCounterValue = getIntent().getStringExtra("counterValue");
        Log.d("CREATION", "onCreate: resolvedCounterValue = " + resolvedCounterValue);

        updateCounterValueFromFile();

//        setSupportActionBar(toolbar);
//        btnTakePhoto = findViewById(R.id.btnTakePic);
//        if (Build.VERSION.SDK_INT >= 23){s
//            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
//        }
        imageView = findViewById(R.id.imageView);

        //--------- This segment is incharge of the random mail button at the bottom of the view
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //---------dont woory about this till later lol


        //--In charge of what happens when the button is clicked,----
//        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                dispatchPictureAction();
//            }
//        });

    }

    int lessonCounter = 0;

    public void load(){
        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_NAME + resolvedCounterValue);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null){
                sb.append(text).append("\n");
            }
            textbox.setText(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateCounterValueFromFile(){
        FileInputStream fis = null;
        try {
            fis = openFileInput(COUNTER_FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            int value;
            value = br.read();
//            mEditText.setText(sb.toString());
            lessonCounter = value;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null){
                try {
                    fis.close();
                    load(); // after the countercalue has been update, we ask to load in the correct locally stored text
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //---- I think this is to check if the app that handled the photo did a good job lol
//    @Override
//    protected void onActivityResult (int requestCode, int resultCode, Intent data){
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == RESULT_OK){
//            if (requestCode == 1){
//                Bitmap bitmap = BitmapFactory.decodeFile("Pictures");
//                imageView.setImageBitmap(bitmap);
//            }
//        }
//    }

//    private void dispatchPictureAction(){
//        Intent takepic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takepic.resolveActivity(getPackageManager()) != null){ // this is to see if there is another app that wants this picture acitvity
//            File photoFile = null;
//            photoFile = createPhotoFile();
//
//            if(photoFile != null){
//                pathToFile = photoFile.getAbsolutePath();
//                Uri photoURI = FileProvider.getUriForFile(this, "com.example.attendanceapp.fileprovider", photoFile);
//                takepic.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                startActivityForResult(takepic, 1);
//            }
//        }
//    }

//    private File createPhotoFile(){
//        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        File storageDir = getExternalStoragePublicDirectory("Pictures");
//        File image = null;
//        try {
//            image = File.createTempFile(name, ".jpg", storageDir);
//        } catch (IOException e) {
//            Log.d("myLog", "Excep : " + e.toString() );
//        }
//        return image;
//    }



}
