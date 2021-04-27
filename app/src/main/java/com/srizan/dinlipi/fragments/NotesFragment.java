package com.srizan.dinlipi.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.srizan.dinlipi.R;
import com.srizan.dinlipi.data.Note;
import com.srizan.dinlipi.data.NoteAdapter;
import com.srizan.dinlipi.data.NoteViewModel;

import java.util.List;

// TODO (8) Implement GreenAdapter.ListItemClickListener from the MainActivity or Fragment
public class NotesFragment extends Fragment implements NoteAdapter.ListItemClickListener {
    private NoteViewModel noteViewModel;
    // TODO (9) Create a Toast variable called mToast to store the current Toast
    private Toast mToast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Toast.makeText(getActivity(), "onCreateView", Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_notes);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final NoteAdapter adapter = new NoteAdapter(this);

        recyclerView.setAdapter(adapter);

        //noteViewModel= new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel = new ViewModelProvider(requireActivity()).get(NoteViewModel.class);

        noteViewModel.getAllNotes().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //Update RecyclerView
                adapter.setNotes(notes);

            }
        });
        //Toast.makeText(getActivity(), "onViewCreated", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        if (mToast!=null){
            mToast.cancel();
        }
        mToast = Toast.makeText(requireActivity(),"Item #" + clickedItemIndex + "Clicked",Toast.LENGTH_SHORT);
        mToast.show();
        NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment);
        navController.navigate(R.id.action_notesFragment_to_noteEditorFragment);
    }
}