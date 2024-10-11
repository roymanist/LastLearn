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
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {

private final NoteRepository repository;
    @Override
    public List<Note> readNoteDB() {
        return repository.findAll();
    }

    @Override
    public Note findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void updateNote(Note note, NoteDTO noteDTO) {

        final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";

       if(noteDTO.getName() != null) {note.setName(noteDTO.getName());}
        if(noteDTO.getText() != null) {note.setText(noteDTO.getText());}
            if(noteDTO.getEmail() != null && Pattern.matches(EMAIL_REGEX, noteDTO.getEmail())) {note.setEmail(noteDTO.getEmail());}
       note.setDateLastChange(LocalDateTime.now());
        repository.save(note);
    }

    @Override
    public void createNote(String nameNote, String textNote,String email) {
        Note note = new Note();
        note.setName(nameNote);
        note.setText(textNote);
        note.setEmail(email);
        note.setDateCreate(LocalDateTime.now());
        note.setDateLastChange(LocalDateTime.now());
        repository.save(note);

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
    public void deleteById(Long id) {
        repository.deleteById(id);
        System.out.println("Success delete note");

    }

    @Override
    public List<Note> findByName(String name) {

        return repository.findByName(name);
    }




}
