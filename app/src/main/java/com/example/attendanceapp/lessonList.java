package com.example.attendanceapp;

import android.content.Intent;
import android.support.constraint.solver.widgets.ConstraintHorizontalLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class lessonList extends AppCompatActivity {

    int lessonCounter = 0; // grow everytime that a lesson is created (this should be stored locally)
    private static final String COUNTER_FILE_NAME = "counterexample.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);

        Button button = (Button) findViewById(R.id.addBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLesson();
            }
        });
    }

    public void addLesson(){
        lessonCounter = lessonCounter + 1;
        updateCounterFile();
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.lesson_object, null);

        view.setId(lessonCounter);
        final Intent intent = new Intent(this, MediaActivity.class);
        String counterId = "" + view.getId();
        Log.d("CREATION", "addLesson: counterID: " + counterId);
        intent.putExtra("counterValue", counterId);

        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(intent);
            }
        });

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        linearLayout.addView(view);

        lessonDataForm();
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

    public void lessonDataForm(){
        Intent intent = new Intent(this, populateLessonForm.class);
        startActivity(intent);
    }


}
