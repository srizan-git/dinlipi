package com.srizan.dinlipi.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NoteDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dinlipi.db";
    private static final int DATABASE_VERSION = 1;
    
    public NoteDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_NOTES_TABLE =  "CREATE TABLE " + DinlipiContract.NoteEntry.TABLE_NAME + " ("
                + DinlipiContract.NoteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DinlipiContract.NoteEntry.COLUMN_NOTE_TITLE + " TEXT NOT NULL, "
                + DinlipiContract.NoteEntry.COLUMN_NOTE_TEXT + " TEXT);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_NOTES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
