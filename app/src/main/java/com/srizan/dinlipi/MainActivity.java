package com.srizan.dinlipi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.srizan.dinlipi.dialog.ReminderEditorDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    NavController navController;
    BottomNavigationView bottomNavigationView;


    FloatingActionButton fab, fabNote, fabReminder, fabVoiceNote;
    Animation animFabOpen, animFabClose, animFabRotateClockWise, animFabRotateAntiClockWise;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

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
                fabNote.setVisibility(View.GONE);
                fabReminder.setVisibility(View.GONE);
                fabVoiceNote.setVisibility(View.GONE);
                fab.setVisibility(View.GONE);
                navController.navigate(R.id.noteEditorFragment);
                break;

            case R.id.fab_add_reminder:
                toggle();

                ReminderEditorDialog editorDialog = new ReminderEditorDialog();
                editorDialog.show(getSupportFragmentManager(),"asd");
                break;
                
            case R.id.fab_add_voice_note:
                toggle();
                navController.navigate(R.id.voiceRecorderFragment);
                break;

        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menu_settings) {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}