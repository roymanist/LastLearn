package com.NotebookPB.HopeFinish.controller;

import com.NotebookPB.HopeFinish.model.Note;
import com.NotebookPB.HopeFinish.service.NoteService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
@AllArgsConstructor

public class NoteController {

    private NoteService service;

    @GetMapping("/read")
public List<Note> readNoteDB(){
        return service.readNoteDB();
    }
    @GetMapping("/create")
    public List<Note> createNote(@RequestParam String NameNote, @RequestParam String TextNote)
    {
        return service.createNote(NameNote,TextNote);
    }
    @GetMapping("/deleteByName")
    @Transactional
    public List<Note> deleteByNote(@RequestParam String NameNote)
    {
        return service.deleteByName(NameNote);
    }
}
