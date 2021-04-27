package com.srizan.dinlipi.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.srizan.dinlipi.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder>  {
    private List<Note> notes = new ArrayList<>();
    // TODO (3) Create a final private ListItemClickListener called mOnClickListener
    private final ListItemClickListener mOnClickListener;

    // TODO (4) Add a ListItemClickListener as a parameter to the constructor and store it in mOnClickListener
    public NoteAdapter(ListItemClickListener listener) {
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_notes, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        //Set view items
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getNoteText());

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    // TODO (5) Implement OnClickListener in the NumberViewHolder class
    class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewTitle;
        private TextView textViewDescription;

        public NoteHolder(@NonNull View itemView) {
            //itemView is the card view
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title_preview);
            textViewDescription = itemView.findViewById(R.id.text_view_note_text_preview);
            // TODO (7) Call setOnClickListener on the View passed into the constructor (use 'this' as the OnClickListener)
            itemView.setOnClickListener(this);
        }


        // TODO (6) Override onClick, passing the clicked item's position (getAdapterPosition()) to mOnClickListener via its onListItemClick method
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }


    // TODO (1) Add an interface called ListItemClickListener
    public interface ListItemClickListener{
        // TODO (2) Within that interface, define a void method called onListItemClick that takes an int as a parameter
        void onListItemClick(int clickedItemIndex);
    }
}
