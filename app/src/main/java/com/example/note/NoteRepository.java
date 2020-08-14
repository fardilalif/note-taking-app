package com.example.note;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    public NoteRepository(Application application){
        NoteDatabase db = NoteDatabase.getInstance(application);
        noteDao = db.noteDao();
        allNotes = noteDao.getAll();
    }

    public void insert(Note note){
        executorService.execute(new insertTask(noteDao, note));
    }

    public void update(Note note){
        executorService.execute(new updateTask(noteDao, note));
    }

    public void delete(Note note){
        executorService.execute(new deleteTask(noteDao, note));
    }

    public void deleteAll(){
        executorService.execute(new deleteAllTask(noteDao));
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    private static class insertTask implements Runnable{
        private NoteDao noteDao;
        private Note note;

        public insertTask(NoteDao noteDao, Note note){
            this.noteDao = noteDao;
            this.note = note;
        }

        @Override
        public void run() {
            noteDao.insert(note);
        }
    }

    private static class updateTask implements Runnable{
        private NoteDao noteDao;
        private Note note;

        public updateTask(NoteDao noteDao, Note note){
            this.noteDao = noteDao;
            this.note = note;
        }

        @Override
        public void run() {
            noteDao.update(note);
        }
    }

    private static class deleteTask implements Runnable{
        private NoteDao noteDao;
        private Note note;

        public deleteTask(NoteDao noteDao, Note note){
            this.noteDao = noteDao;
            this.note = note;
        }

        @Override
        public void run() {
            noteDao.delete(note);
        }
    }

    private static class deleteAllTask implements Runnable{
        private NoteDao noteDao;
        private Note note;

        public deleteAllTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        public void run() {
            noteDao.deleteAll();
        }
    }
}
