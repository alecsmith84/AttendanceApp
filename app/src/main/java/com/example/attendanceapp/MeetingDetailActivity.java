package com.example.attendanceapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class MeetingDetailActivity extends AppCompatActivity {

    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_detail_activity);

        final EditText name = findViewById(R.id.name);
        final EditText description = findViewById((R.id.description));
        final EditText notes = findViewById(R.id.notes);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            //arrayIndex = extras.getInt("INDEX");

            name.setText(extras.getString("NAME"));
            description.setText(extras.getString("DESCRIPTION"));
            notes.setText(extras.getString("NOTES"));
        }
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //toolbar.setTitle(getTitle());

        //View recyclerView = findViewById(R.id.movie_list);
        //assert recyclerView != null;
        //setupRecyclerView((RecyclerView) recyclerView);

        /*name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    meetingMain.setMeetingsName(arrayIndex, name.getText().toString());

                    return true;
                }
                return false;
            }
        });*/
    }

    //public void setActivityReference(MeetingActivity m){
    //    meetingMain = m;
    //}
}
