package com.srizan.dinlipi.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.srizan.dinlipi.R;

public class NoteCursorAdapter  extends CursorAdapter {
    public NoteCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_notes,parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView noteTitle = view.findViewById(R.id.note_title);
        TextView noteText = view.findViewById(R.id.note_preview);
        TextView noteTime = view.findViewById(R.id.noteTime);
        TextView noteDay = view.findViewById(R.id.noteDay);
        TextView noteDate = view.findViewById(R.id.noteDate);

        noteTitle.setText("Title");
        noteText.setText("ashdcb");
        noteTime.setText("Time");
        noteDay.setText("Day");
        noteDate.setText("Date");

    }
}
