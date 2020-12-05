package com.srizan.dinlipi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.srizan.dinlipi.MainActivity;
import com.srizan.dinlipi.R;
import com.srizan.dinlipi.model.Note;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<Note> {

    NavController navController;

    public NoteAdapter(@NonNull Context context, ArrayList<Note> noteArrayList) {
        super(context, 0, noteArrayList);
        this.navController=navController;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View noteListView = convertView;
        if (noteListView == null){
            noteListView = LayoutInflater.from(getContext()).inflate(R.layout.list_notes, parent, false);

        }

        Note currentNote = getItem(position);

        TextView noteTitle = noteListView.findViewById(R.id.note_title);
        noteTitle.setText(currentNote.getNoteTitle());

        TextView noteText = noteListView.findViewById(R.id.note_preview);
        noteText.setText(currentNote.getNoteText());

        TextView noteTime = noteListView.findViewById(R.id.noteTime);
        noteTime.setText("12:00");

        TextView noteDay = noteListView.findViewById(R.id.noteDay);
        noteDay.setText("Sunday");

        TextView noteDate = noteListView.findViewById(R.id.noteDate);
        noteDate.setText("30");

        noteListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        return noteListView;
    }
}
