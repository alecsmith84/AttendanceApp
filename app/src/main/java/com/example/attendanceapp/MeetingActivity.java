package com.example.attendanceapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

public class MeetingActivity extends AppCompatActivity {
    //private String[] testArray;
    public static final List<MeetingDetailModel> Meetings = new ArrayList<MeetingDetailModel>();
    public int meetingNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if(Meetings.size() == 0) {

            MeetingDetailModel test = new MeetingDetailModel("Day 1", "This is Day1");
            Meetings.add(test);
            test = new MeetingDetailModel("Day 15", "This is Day2");
            Meetings.add(test);
            test = new MeetingDetailModel("Day 29", "This is Day3");
            Meetings.add(test);
        }

        View recyclerView = findViewById(R.id.movie_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
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
            holder.mIdView.setText(mValues.get(position).getName());
            //holder.mContentView.setText(mValues.get(position).getMovieYear());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, MeetingDetailActivity.class);
                    intent.putExtra("NAME", holder.mItem.getName());

                    context.startActivity(intent);
                    //startActivity(new Intent(MeetingActivity.this, MeetingDetailActivity.class));
                }
            });
        }

        @Override
        public int getItemCount() {
            // CS315: DO THIS
            // TODO: BUG FIX - Figure out why our movie list gets re-added every time we come back to this Activity
            // TODO: it could be in THIS class, OR in the DumbMovieContent class, or maybe even somewhere else?
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
