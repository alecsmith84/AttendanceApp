package com.example.attendanceapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;



public class MeetingActivity extends AppCompatActivity {
    //private String[] testArray;
    public final List<MeetingDetailModel> Meetings = new ArrayList<MeetingDetailModel>();
    public int meetingNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Meetings");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        if(Meetings.size() == 0) {

            MeetingDetailModel test = new MeetingDetailModel("Day 1", "Twas a great day", "This is Day1");
            Meetings.add(test);
            test = new MeetingDetailModel("Day 15", "Pumpkin day", "This is Day2");
            Meetings.add(test);
            test = new MeetingDetailModel("Day 29", "Safety Briefing 6", "This is Day3");
            Meetings.add(test);
        }

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                MeetingDetailModel newMeeting = new MeetingDetailModel("","","");
                Meetings.add(newMeeting);

                Snackbar.make(view, "Loading new meeting #" + Integer.toString(Meetings.size()), Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

                Context context = view.getContext();
                Intent intent = new Intent(context, MeetingDetailActivity.class);

                //intent.putExtra("INDEX", Meetings.size()-1);
                intent.putExtra("NAME", Meetings.get(Meetings.size()-1).getName());
                intent.putExtra("DESCRIPTION", Meetings.get(Meetings.size()-1).getDescription());
                intent.putExtra("NOTES", Meetings.get(Meetings.size()-1).getNotes());

                context.startActivity(intent);
            }
        });


        View recyclerView = findViewById(R.id.meeting_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        View recyclerView = findViewById(R.id.meeting_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }


    public void setMeetingsName(int index, String s){
        Meetings.get(index).setName(s);
    }
    public void setMeetingsDescription(int index, String s){
        Meetings.get(index).setDescription(s);
    }
    public void setMeetingsNotes(int index, String s){
        Meetings.get(index).setNotes(s);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        //movieMaker.createMovieMagic();
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(Meetings));//movieMaker.MOVIES)); // pass that array... I don't care how you got it...
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<MeetingDetailModel> mValues;

        public SimpleItemRecyclerViewAdapter(List<MeetingDetailModel> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.meeting_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            meetingNumber = position;
            if(!mValues.get(position).getName().equals("")) {
                holder.mIdView.setText(mValues.get(position).getName());
            }else{
                holder.mIdView.setText("New Meeting");
            }

            //holder.mContentView.setText(mValues.get(position).getMovieYear());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Snackbar.make(v, "Loading Meeting...", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();

                    Context context = v.getContext();
                    Intent intent = new Intent(context, MeetingDetailActivity.class);
                    intent.putExtra("NAME", holder.mItem.getName());
                    intent.putExtra("DESCRIPTION", holder.mItem.getDescription());
                    intent.putExtra("NOTES", holder.mItem.getNotes());

                    context.startActivity(intent);
                    //startActivity(new Intent(MeetingActivity.this, MeetingDetailActivity.class));
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public MeetingDetailModel mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
