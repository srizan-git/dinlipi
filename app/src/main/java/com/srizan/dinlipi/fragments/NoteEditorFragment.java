package com.srizan.dinlipi.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.srizan.dinlipi.R;
import com.srizan.dinlipi.data.Note;
import com.srizan.dinlipi.data.NoteViewModel;


public class NoteEditorFragment extends Fragment {
    private NoteViewModel noteViewModel;
    EditText noteTitle, noteText;
    private Note note;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_editor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        noteTitle = view.findViewById(R.id.edit_text_note_title);
        noteText = view.findViewById(R.id.edit_text_note_text);
        noteViewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);

    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_toolbar_note_editor, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menu_save){

            String title = noteTitle.getText().toString();
            String text = noteText.getText().toString();
            note = new Note(title,text);

            noteViewModel.insert(note);
            Toast.makeText(requireActivity(), "Saved", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}