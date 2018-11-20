package com.example.attendanceapp;

import java.util.Calendar;

public class MeetingDetailModel {
    private String name;
    private String description;
    private String notes;
    private String date;
    private String ID;

    public MeetingDetailModel(String n1, String n2, String n3, String id){
        setName(n1);
        setDescription(n2);
        setNotes(n3);
        setDateToToday();
        setID(id);
    }

    public void setName(String n){
        name = n;
    }
    public String getName(){
        return name;
    }
    public void setDescription(String n){ description = n; }
    public String getDescription(){
        return description;
    }
    public void setNotes(String n){
        notes = n;
    }
    public String getNotes(){
        return notes;
    }
    public void setDate(String n) { date = n; }
    public String getDate() { return date; }
    public void setID(String id){ID = id;}
    public String getID(){return ID;}
    public void setDateToToday(){
        Calendar calendar = Calendar.getInstance();
        int thisYear = calendar.get(Calendar.YEAR);
        int thisMonth = calendar.get(Calendar.MONTH);
        int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
        String calenderOut = Integer.toString(thisMonth) + "/" + Integer.toString(thisDay) + "/" + Integer.toString(thisYear);

        setDate(calenderOut);
    }
    public boolean isToday(){
        Calendar calendar = Calendar.getInstance();
        int thisYear = calendar.get(Calendar.YEAR);
        int thisMonth = calendar.get(Calendar.MONTH);
        int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
        String calenderOut = Integer.toString(thisMonth) + "/" + Integer.toString(thisDay) + "/" + Integer.toString(thisYear);

        return (getDate() == calenderOut);
    }
}
