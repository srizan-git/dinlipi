package com.srizan.dinlipi.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.srizan.dinlipi.converters.DateConverter;

@Database(entities = {Note.class}, version = 1)
//@TypeConverters(DateConverter.class)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();
    private static NoteDatabase INSTANCE;

    public static NoteDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoteDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here.
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoteDatabase.class, "note_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // This callback is called when the database has opened.
    // In this case, use PopulateDbAsync to populate the database
    // with the initial data set if the database has no entries.
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final NoteDao mDao;
        // Initial data set


        PopulateDbAsync(NoteDatabase db) {
            mDao = db.noteDao();
        }
        @Override
        protected Void doInBackground(final Void... params) {
            // If we have no words, then create the initial list of words.
           
            return null;
        }
    }
}
