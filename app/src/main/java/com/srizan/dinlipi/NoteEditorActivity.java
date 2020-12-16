package com.srizan.dinlipi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.srizan.dinlipi.data.DinlipiContract;

public class NoteEditorActivity extends AppCompatActivity {

    EditText mTitle;
    EditText mNote;

    private Uri mCurrentPetUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        Intent intent = getIntent();

        mCurrentPetUri = intent.getData();

        String tittle = getIntent().getStringExtra("title");
        String note = getIntent().getStringExtra("note");

        mTitle = findViewById(R.id.edit_text_title);
        mNote = findViewById(R.id.edit_text_note);

        mTitle.setText(tittle);
        mNote.setText(note);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_editor,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){
            case R.id.action_save:
                save();
                break;
            case R.id.action_delete:
                
                deleteNote();


                Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show();
                NavUtils.navigateUpFromSameTask(this);

        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteNote() {

        // Only perform the delete if this is an existing pet.
        if (mCurrentPetUri != null) {
            // Call the ContentResolver to delete the pet at the given content URI.
            // Pass in null for the selection and selection args because the mCurrentPetUri
            // content URI already identifies the pet that we want.
            int rowsDeleted = getContentResolver().delete(mCurrentPetUri, null, null);

            // Show a toast message depending on whether or not the delete was successful.
            if (rowsDeleted == 0) {
                // If no rows were deleted, then there was an error with the delete.
                Toast.makeText(this, "Failed!",
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the delete was successful and we can display a toast.
                Toast.makeText(this, "Deleted",
                        Toast.LENGTH_SHORT).show();
            }
        }

        // Close the activity
        finish();
    }

    private void save() {

        ContentValues values = new ContentValues();
        values.put(DinlipiContract.NoteEntry.COLUMN_NOTE_TITLE, mTitle.getText().toString());
        values.put(DinlipiContract.NoteEntry.COLUMN_NOTE_TEXT, mNote.getText().toString());

        Uri uri = getContentResolver().insert(DinlipiContract.NoteEntry.CONTENT_URI, values);
        if (uri == null) {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
        }

        NavUtils.navigateUpFromSameTask(this);
    }
}