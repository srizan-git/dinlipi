package com.srizan.dinlipi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.srizan.dinlipi.adapters.NoteAdapter;
import com.srizan.dinlipi.adapters.NoteCursorAdapter;
import com.srizan.dinlipi.data.DinlipiContract;
import com.srizan.dinlipi.dialog.ReminderEditorDialog;
import com.srizan.dinlipi.model.Note;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity implements View.OnClickListener {


    BottomNavigationView bottomNavigationView;
    FloatingActionButton fab, fabNote, fabReminder, fabVoiceNote;
    Animation animFabOpen, animFabClose, animFabRotateClockWise, animFabRotateAntiClockWise;
    boolean isOpen = false;

    NoteCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        //Initialize Floating Action Buttons
        fab = findViewById(R.id.fab);
        fabNote = findViewById(R.id.fab_add_note);
        fabReminder = findViewById(R.id.fab_add_reminder);
        fabVoiceNote = findViewById(R.id.fab_add_voice_note);

        //Initialize Animations
        animFabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        animFabClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        animFabRotateClockWise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        animFabRotateAntiClockWise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);
        //Bottom Nab Code
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.nav_reminder){
                    Intent intent = new Intent(NoteActivity.this,ReminderActivity.class);
                    startActivity(intent);
                }

                if (item.getItemId()==R.id.nav_voiceNotes){
                    Intent intent = new Intent(NoteActivity.this,VoiceNotesActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });

        fabNote.setOnClickListener(this);
        fabReminder.setOnClickListener(this);
        fabVoiceNote.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayNotes();
    }

    private void displayNotes() {

        String[] projection = {
                DinlipiContract.NoteEntry._ID,
                DinlipiContract.NoteEntry.COLUMN_NOTE_TITLE,
                DinlipiContract.NoteEntry.COLUMN_NOTE_TEXT};


        // Perform a query on the provider using the ContentResolver.
        // Use the {@link PetEntry#CONTENT_URI} to access the pet data.
        Cursor cursor = getContentResolver().query(
                DinlipiContract.NoteEntry.CONTENT_URI,
                projection,null,null,null);


        ListView listView = findViewById(R.id.note_list_view);

        ArrayList<Note> noteArrayList = new ArrayList<>();



        while (cursor.moveToNext()){
            noteArrayList.add(new Note( cursor.getString(1), cursor.getString(2)));
        }

        NoteAdapter noteAdapter = new NoteAdapter(this,noteArrayList);
        listView.setAdapter(noteAdapter);

    }

    private void toggle() {
        if(isOpen){
            fab.startAnimation(animFabRotateAntiClockWise);
            fabNote.startAnimation(animFabClose);
            fabReminder.startAnimation(animFabClose);
            fabVoiceNote.startAnimation(animFabClose);

            fabNote.setClickable(false);
            fabReminder.setClickable(false);
            fabVoiceNote.setClickable(false);
            isOpen = false;
        }
        else {
            fab.startAnimation(animFabRotateClockWise);
            fabNote.startAnimation(animFabOpen);
            fabReminder.startAnimation(animFabOpen);
            fabVoiceNote.startAnimation(animFabOpen);

            fabNote.setClickable(true);
            fabReminder.setClickable(true);
            fabVoiceNote.setClickable(true);
            isOpen = true;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fab_add_note:
                toggle();
                Intent intent = new Intent(NoteActivity.this,NoteEditorActivity.class);
                startActivity(intent);

                break;

            case R.id.fab_add_reminder:
                toggle();
                Intent intent2 = new Intent(NoteActivity.this,ReminderEditorActivity.class);
                startActivity(intent2);

                break;

            case R.id.fab_add_voice_note:
                toggle();
                Intent intent3 = new Intent(NoteActivity.this,VoiceRecorderActivity.class);
                startActivity(intent3);
                break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menu_settings) {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId()==R.id.action_delete_all_notes){

            int rowsDeleted = getContentResolver().delete(DinlipiContract.NoteEntry.CONTENT_URI, null, null);
            displayNotes();

        }
        return super.onOptionsItemSelected(item);
    }

}