package com.example.attendanceapp;

public class Person {

    public Person(String NAME, int HERE){
        if(HERE == 1) {
            isHere = true;
        }else{
            isHere = false;
        }
        name = NAME;
    }
    public int getHere(){
        if(isHere){
            return 1;
        }else{
            return 2;
        }
    }
    public boolean isHere;
    public String name;
    public int ID;
}
