package com.srizan.dinlipi.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Note {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String title;
    private String noteText;
    
    public Note(String title, String noteText) {
        this.title = title;
        this.noteText = noteText;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNoteText() {
        return noteText;
    }
}
