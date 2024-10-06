package com.NotebookPB.HopeFinish.service;

import com.NotebookPB.HopeFinish.model.Note;

import java.util.List;

public interface NoteService {

    List<Note> readNoteDB();
    List<Note> createNote(String nameNote, String textNote);
    List<Note> deleteNote(Note note);
    List<Note> deleteByName(String name);


}
