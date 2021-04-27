package com.srizan.dinlipi.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.srizan.dinlipi.data.Note;
import com.srizan.dinlipi.data.NoteDao;
import com.srizan.dinlipi.data.NoteDatabase;

import java.util.List;

public class NoteRepository {

    private NoteDao mNoteDao;
    private LiveData<List<Note>> mAllNotes;

    NoteRepository(Application application){
        NoteDatabase db = NoteDatabase.getDatabase(application);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getAllNotes();
    }



    public void insert(Note note){
        new InsertNoteAsyncTask(mNoteDao).execute(note);
    }
    public void update(Note note){  new UpdateNoteAsyncTask(mNoteDao).execute(note); }
    public void delete(Note note){  new DeleteNoteAsyncTask(mNoteDao).execute(note); }
    public void deleteAllNotes(){
        new DeleteAllNotesAsyncTask(mNoteDao).execute();
    }
    public LiveData<List<Note>> getAllNotes(){        return mAllNotes; }

    // Async Tasks to run CRUD Operations in Background thread
    public static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void> {
        private NoteDao noteDao;
        private InsertNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;

        }
        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.insertNote(notes[0]);
            return null;
        }
    }

    //Update AsyncTask
    public static class UpdateNoteAsyncTask extends AsyncTask<Note,Void,Void> {
        private NoteDao noteDao;
        private UpdateNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;

        }
        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.updateNote(notes[0]);
            return null;
        }
    }
    public static class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void> {
        private NoteDao noteDao;
        private DeleteNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;

        }
        @Override
        protected Void doInBackground(Note... notes) {

            noteDao.deleteNote(notes[0]);
            return null;
        }
    }
    public static class DeleteAllNotesAsyncTask extends AsyncTask<Void,Void,Void> {
        private NoteDao noteDao;
        private DeleteAllNotesAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;

        }
        @Override
        protected Void doInBackground(Void... notes) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
