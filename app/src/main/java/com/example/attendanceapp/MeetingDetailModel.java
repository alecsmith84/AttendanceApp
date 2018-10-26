package com.example.attendanceapp;

public class MeetingDetailModel {
    private String name;
    private String description;
    private String notes;

    public MeetingDetailModel(String n1, String n2, String n3){
        setName(n1);
        setDescription(n2);
        setNotes(n3);
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
}
