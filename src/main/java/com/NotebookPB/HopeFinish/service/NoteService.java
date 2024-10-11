package com.NotebookPB.HopeFinish.service;

import com.NotebookPB.HopeFinish.model.Note;
import com.NotebookPB.HopeFinish.model.NoteDTO;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    List<Note> readNoteDB();
    void createNote(String nameNote, String textNote, String email);
    void createNoteFull(NoteDTO noteDTO);
    void deleteById(Long id);
    List<Note> findByName(String name);
    Note findById(Long id);
    void updateNote(Note note,NoteDTO noteDTO);



}
