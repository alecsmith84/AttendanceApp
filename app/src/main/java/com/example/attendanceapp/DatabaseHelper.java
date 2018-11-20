package com.example.attendanceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "people_table";
    private static final String TABLE_NAME2 = "meetings_table";
    private static final String TABLE_NAME3 = "attendance_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";


    public DatabaseHelper(Context context) {
        super(context, "coolDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT)";
        db.execSQL(createTable);

        createTable = "CREATE TABLE " + TABLE_NAME2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, DESCRIPTION TEXT, NOTES TEXT)";
        db.execSQL(createTable);

        createTable = "CREATE TABLE " + TABLE_NAME3 + " (meeting_ID INTEGER, person_ID INTEGER, isPresent INTEGER DEFAULT 0)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        onCreate(db);
    }

    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public String addData2(String item1, String item2, String item3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TITLE", item1);
        contentValues.put("DESCRIPTION", item2);
        contentValues.put("NOTES", item3);

        Log.d(TAG, "addData: Adding " + item1 + ", " + item2 + ", " + item3 +" to " + TABLE_NAME2);

        long result = db.insert(TABLE_NAME2, null, contentValues);

        String query = "SELECT * FROM " + TABLE_NAME2 + " WHERE TITLE = '" + item1 + "' AND DESCRIPTION = '" + item2 + "' AND NOTES = '" + item3 + "'";
        Cursor data = db.rawQuery(query, null);
        data.moveToNext();

        return data.getString(0);
    }

    public Boolean addData3(int item1, int item2, int item3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("meeting_ID", item1);
        contentValues.put("person_ID", item2);
        contentValues.put("isPresent", item3);

        Log.d(TAG, "addData: Adding " + item1 + ", " + item2 + ", " + item3 +" to " + TABLE_NAME2);

        long result = db.insert(TABLE_NAME3, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    //(ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, DESCRIPTION TEXT, NOTES TEXT)";
    public void updateData2(String item1, String item2, String item3, int m_id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME2 + " SET TITLE = '" + item1 + "' WHERE ID = " + m_id;
        db.execSQL(query);
        query = "UPDATE " + TABLE_NAME2 + " SET DESCRIPTION = '" + item2 + "' WHERE ID = " + m_id;
        db.execSQL(query);
        query = "UPDATE " + TABLE_NAME2 + " SET NOTES = '" + item3 + "' WHERE ID = " + m_id;
        db.execSQL(query);
    }
    public void updateData3(int item1, int item2, int item3){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME3 + " SET isPresent = " + item3 + " WHERE person_ID = " + item2 + " AND meeting_ID = " + item1;
        db.execSQL(query);
    }


    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getData2(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME2;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getData3(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME3;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getAbsense(int p_id, int m_id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME3 + " WHERE person_ID = " + p_id + " AND meeting_ID = " + m_id;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " LIKE '%" + name + "%'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    //purely for debugging/testing
    public void clearDatabase(String TABLE_NAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        String clearDBQuery = "DELETE FROM "+TABLE_NAME;
        db.execSQL(clearDBQuery);
    }


    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }


    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

}