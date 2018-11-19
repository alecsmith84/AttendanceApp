package com.example.attendanceapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.example.attendanceapp.Person;

public class MeetingDetailActivity extends AppCompatActivity {

    public final List<Person> People = new ArrayList<Person>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_detail_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        View recyclerView = findViewById(R.id.person_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        People.add(new Person("Thomas"));
        People.add(new Person("Adam"));
        People.add(new Person("Daniel"));
        People.add(new Person("Will"));
        People.add(new Person("Timothy"));
        People.add(new Person("Nate"));
        People.add(new Person("Aaron"));

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

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        //movieMaker.createMovieMagic();
        recyclerView.setAdapter(new MeetingDetailActivity.SimpleItemRecyclerViewAdapter(People));//movieMaker.MOVIES)); // pass that array... I don't care how you got it...
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<MeetingDetailActivity.SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Person> mValues;

        public SimpleItemRecyclerViewAdapter(List<Person> items) {
            mValues = items;
        }

        @Override
        public MeetingDetailActivity.SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.person_list_content, parent, false);
            return new MeetingDetailActivity.SimpleItemRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MeetingDetailActivity.SimpleItemRecyclerViewAdapter.ViewHolder holder, final int position) {
            holder.mItem = mValues.get(position);

            //meetingNumber = position;

            String temp;
            if(mValues.get(position).isHere){
                temp = "is here";
            }else{
                temp = "is not here";
            }
            if(!mValues.get(position).name.equals("")) {
                holder.mIdView.setText(mValues.get(position).name + " : " + temp);
            }else{
                holder.mIdView.setText("New Person" + " : " + temp);
            }

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mValues.get(position).isHere = !mValues.get(position).isHere;
                    String temp;
                    if(mValues.get(position).isHere){
                        temp = "is here";
                    }else {
                        temp = "is not here";
                    }
                    if(!mValues.get(position).name.equals("")) {
                        holder.mIdView.setText(mValues.get(position).name + " : " + temp);
                    }else{
                        holder.mIdView.setText("New Person" + " : " + temp);
                    }
                }
            });





            //holder.mContentView.setText(mValues.get(position).getMovieYear());


        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public Person mItem;

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
    //public void setActivityReference(MeetingActivity m){
    //    meetingMain = m;
    //}
}
