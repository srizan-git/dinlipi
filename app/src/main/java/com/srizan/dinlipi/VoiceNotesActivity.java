package com.srizan.dinlipi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.srizan.dinlipi.dialog.ReminderEditorDialog;

public class VoiceNotesActivity extends AppCompatActivity implements View.OnClickListener {
    BottomNavigationView bottomNavigationView;
    FloatingActionButton fab, fabNote, fabReminder, fabVoiceNote;
    Animation animFabOpen, animFabClose, animFabRotateClockWise, animFabRotateAntiClockWise;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_notes);


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
                if (item.getItemId()==R.id.nav_notes){
                    Intent intent = new Intent(VoiceNotesActivity.this,NoteActivity.class);
                    startActivity(intent);
                }

                if (item.getItemId()==R.id.nav_reminder){
                    Intent intent = new Intent(VoiceNotesActivity.this,ReminderActivity.class);
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
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fab_add_note:
                toggle();
                Intent intent = new Intent(VoiceNotesActivity.this,NoteEditorActivity.class);
                startActivity(intent);

                break;

            case R.id.fab_add_reminder:
                toggle();

                Intent intent2 = new Intent(VoiceNotesActivity.this,ReminderEditorActivity.class);
                startActivity(intent2);
                break;

            case R.id.fab_add_voice_note:
                toggle();
                Intent intent3 = new Intent(VoiceNotesActivity.this,VoiceRecorderActivity.class);
                startActivity(intent3);
                break;

        }

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
}