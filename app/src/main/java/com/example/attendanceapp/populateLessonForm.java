package com.example.attendanceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class populateLessonForm extends AppCompatActivity {
    private static final String FILE_NAME = "example.txt";
    EditText mEditText;

    int lessonCounter = 0; // grow everytime that a lesson is created (this should be stored locally)
    private static final String COUNTER_FILE_NAME = "counterexample.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_populate_lesson_form);

        mEditText = findViewById(R.id.LessonTitleInput);

        updateCounterValueFromFile();
    }


    public void updateCounterFile(){
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(COUNTER_FILE_NAME, MODE_PRIVATE);
            fos.write(lessonCounter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void save(View v){
        String text = mEditText.getText().toString();
        FileOutputStream fos = null;

        try {
//            lessonCounter = lessonCounter + 1;
//            updateCounterFile();
            fos = openFileOutput(FILE_NAME + lessonCounter, MODE_PRIVATE);
            fos.write(text.getBytes());

            mEditText.getText().clear();
            Toast.makeText(this, "Saved to" + getFilesDir() + "/" + FILE_NAME + lessonCounter, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null){
                try {
                    fos.close();
                    openMediaActivity();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void openMediaActivity(){
//        Intent intent = new Intent(this, lessonList.class);
//        startActivity(intent);
        finish();
    }

    public void load(View v){
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME + lessonCounter);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null){
                sb.append(text).append("\n");
            }
            mEditText.setText(sb.toString());

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



}
