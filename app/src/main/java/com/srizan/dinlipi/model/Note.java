package com.srizan.dinlipi.model;

import java.util.Date;

public class Note {
    private Date mDate;
    private String mNoteTitle;
    private String mNoteText;

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getNoteTitle() {
        return mNoteTitle;
    }

    public void setNoteTitle(String mNoteTitle) {
        this.mNoteTitle = mNoteTitle;
    }

    public String getNoteText() {
        return mNoteText;
    }

    public void setNoteText(String mNoteText) {
        this.mNoteText = mNoteText;
    }
}
