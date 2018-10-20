package com.example.ahmedaldulaimy.mysmartusc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

/**
 * Created by Mitch on 2016-05-13.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mysmartusc.db";
    public static final String IMPORTANT_EMAILS_TABLE = "emails_list"; // Table for important email addresses
    public static final String URGENT_KEYWORDS_TABLE = "urgent_keywords_list"; // Table for urgent keywords
    public static final String FAVORITE_KEYWORDS_TABLE = "favorite_keywords_list"; // Table for favorite keywords
    public static final String COL1 = "ID";
    public static final String COL2 = "ITEM1";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createImportantEmailsTable = "CREATE TABLE " + IMPORTANT_EMAILS_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " ITEM1 TEXT)";
        String createUrgentKeywordsTable = "CREATE TABLE " + URGENT_KEYWORDS_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " ITEM1 TEXT)";
        String createFavoriteKeywordsTable = "CREATE TABLE " + FAVORITE_KEYWORDS_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " ITEM1 TEXT)";

        db.execSQL(createImportantEmailsTable);
        db.execSQL(createUrgentKeywordsTable);
        db.execSQL(createFavoriteKeywordsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + IMPORTANT_EMAILS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + URGENT_KEYWORDS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FAVORITE_KEYWORDS_TABLE);
        onCreate(db);
    }

    // Add to table for important email addresses.
    public boolean addImportantEmailData(String emailAddress) {
        Log.v("ON LINE 40", "ADD DATA Function");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, emailAddress);

        long result = db.insert(IMPORTANT_EMAILS_TABLE, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            Log.v("ON LINE 40", "ADD DATA Function:Returning FALSE");
            return false;
        } else {
            Log.v("ON LINE 40", "ADD DATA Function:Returning TRUE");
            return true;
        }
    }

    // Get contents of table for important email addresses.
    public Cursor getImportantEmails(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + IMPORTANT_EMAILS_TABLE, null);
        return data;
    }

    // Add to table for urgent keywords.
    public boolean addUrgentKeywordData(String keyword) {
        Log.v("ON LINE 40", "ADD DATA Function");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, keyword);

        long result = db.insert(URGENT_KEYWORDS_TABLE, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            Log.v("ON LINE 40", "ADD DATA Function:Returning FALSE");
            return false;
        } else {
            Log.v("ON LINE 40", "ADD DATA Function:Returning TRUE");
            return true;
        }
    }

    // Get contents of table for urgent keywords.
    public Cursor getUrgentKeywords(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + URGENT_KEYWORDS_TABLE, null);
        return data;
    }

    // Add to table for favorite keywords.
    public boolean addFavoriteKeywordData(String keyword) {
        Log.v("ON LINE 40", "ADD DATA Function");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, keyword);

        long result = db.insert(FAVORITE_KEYWORDS_TABLE, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            Log.v("ON LINE 40", "ADD DATA Function:Returning FALSE");
            return false;
        } else {
            Log.v("ON LINE 40", "ADD DATA Function:Returning TRUE");
            return true;
        }
    }

    // Get contents of table for favorite keywords.
    public Cursor getFavoriteKeywords(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + FAVORITE_KEYWORDS_TABLE, null);
        return data;
    }

    // CURRENTLY NOT WORKING
    public boolean removeData(String item1){
        SQLiteDatabase db = this.getWritableDatabase();
        long result =  db.delete(IMPORTANT_EMAILS_TABLE, COL1 + "=?", new String[]{item1} );
        if(result > 0){
            System.out.println("Succesfully deleted data ");
            return true;
        }
        else {
            System.out.println("ERROR deleted data ");
            return false;
        }

    }
}
