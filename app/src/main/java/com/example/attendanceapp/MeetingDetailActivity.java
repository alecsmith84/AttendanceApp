package com.example.attendanceapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import com.example.attendanceapp.Person;

public class MeetingDetailActivity extends AppCompatActivity {

    public final List<Person> People = new ArrayList<Person>();
    private String m_ID;
    DatabaseHelper mDatabaseHelper;
    //@Override
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

        mDatabaseHelper = new DatabaseHelper(this);

        Cursor people = mDatabaseHelper.getData();
        //Cursor attendance = mDatabaseHelper.getData3();

        while(people.moveToNext()){
            Cursor temp = mDatabaseHelper.getAbsense(people.getInt(0),Integer.parseInt(m_ID));
            while(temp.moveToNext()) {
                People.add(new Person(people.getString(1), temp.getInt(2)));
            }
        }

        toastMessage("TEST");


        final EditText name = findViewById(R.id.name);
        final EditText description = findViewById((R.id.description));
        final EditText notes = findViewById(R.id.notes);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            //arrayIndex = extras.getInt("INDEX");

            name.setText(extras.getString("NAME"));
            description.setText(extras.getString("DESCRIPTION"));
            notes.setText(extras.getString("NOTES"));
            m_ID = extras.getString("ID");
            toastMessage(m_ID);
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

                    mDatabaseHelper.updateData2(name.getText().toString(),description.getText().toString(),notes.getText().toString(),ID);

                    toastMessage("How dare you?");

                    return true;
                }
                toastMessage("Possible Hit");
                return false;

            }
        });*/
        EditText editName = (EditText) findViewById(R.id.name);
        editName.addTextChangedListener(filterTextWatcher);
        EditText editDescription = (EditText) findViewById(R.id.description);
        editDescription.addTextChangedListener(filterTextWatcher);
        EditText editNotes = (EditText) findViewById(R.id.notes);
        editNotes.addTextChangedListener(filterTextWatcher);


        };

        private TextWatcher filterTextWatcher = new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // DO THE CALCULATIONS HERE AND SHOW THE RESULT AS PER YOUR CALCULATIONS
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                final EditText name = findViewById(R.id.name);
                final EditText description = findViewById((R.id.description));
                final EditText notes = findViewById(R.id.notes);
                mDatabaseHelper.updateData2(name.getText().toString(),description.getText().toString(),notes.getText().toString(),Integer.parseInt(m_ID));
                toastMessage("UPDATED DATABASE");
            }
        };

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
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
    //public void setActivityReference(MeetingActivity m){
    //    meetingMain = m;
    //}
}
