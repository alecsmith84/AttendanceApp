package com.example.attendanceapp;

public class MeetingDetailModel {
    private String name;
    private String notes;

    public MeetingDetailModel(String n1, String n2){
        setName(n1);
        setNotes(n2);
    }

    public void setName(String n){
        name = n;
    }
    public String getName(){
        return name;
    }
    public void setNotes(String n){
        notes = n;
    }
    public String getNotes(){
        return notes;
    }
}
