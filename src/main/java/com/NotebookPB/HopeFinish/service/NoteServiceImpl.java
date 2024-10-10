package com.NotebookPB.HopeFinish.service;

import com.NotebookPB.HopeFinish.model.Note;
import com.NotebookPB.HopeFinish.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {

private final NoteRepository repository;
    @Override
    public List<Note> readNoteDB() {
        return repository.findAll();
    }

    @Override
    public List<Note> createNote(String nameNote, String textNote) {
        Note note = new Note();
        note.setName(nameNote);
        note.setText(textNote);
        repository.save(note);
        System.out.println("Success create note");

        return repository.findAll();
    }

    @Override
    public List<Note> createNoteFull(Note note) {

        repository.save(note);
        return repository.findAll();
    }

    @Override
    public List<Note> deleteNote(Note note) {
        return null;
    }

    @Override
    public List<Note> deleteByName(String name) {
        repository.deleteByName(name);
        System.out.println("Success delete note");
        return repository.findAll();
    }

    @Override
    public List<Note> findByName(String name) {

        return repository.findByName(name);
    }

}
