package com.NotebookPB.HopeFinish.service;

import com.NotebookPB.HopeFinish.model.Note;
import com.NotebookPB.HopeFinish.model.NoteDTO;
import com.NotebookPB.HopeFinish.repository.NoteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {

private final NoteRepository repository;
    @Override
    public List<Note> readNoteDB() {
        return repository.findAll();
    }
    public Note findById(Long id) {
        return repository.findById(id).orElse(null);
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
    public void createNoteFull(NoteDTO noteDTO) {
        Note note = new Note();


        note.setName(noteDTO.getName());
        note.setText(noteDTO.getText());
        note.setEmail(noteDTO.getEmail());
        note.setDateCreate(LocalDateTime.now());
        note.setDateLastChange(LocalDateTime.now());
        repository.save(note);

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
