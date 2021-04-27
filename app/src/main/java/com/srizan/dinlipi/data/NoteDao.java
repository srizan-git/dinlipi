package com.srizan.dinlipi.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNote(Note note);
    @Update
    void updateNote(Note... notes);
    @Delete
    void deleteNote(Note note);

    @Query("DELETE FROM notes_table")
    void deleteAllNotes();

    @Query("SELECT * from notes_table LIMIT 1")
    Note[] getAnyNote();

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    LiveData<List<Note>> getAllNotes();




}
