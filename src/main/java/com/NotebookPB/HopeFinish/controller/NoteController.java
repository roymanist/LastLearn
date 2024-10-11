package com.NotebookPB.HopeFinish.controller;

import com.NotebookPB.HopeFinish.model.Note;
import com.NotebookPB.HopeFinish.model.NoteDTO;
import com.NotebookPB.HopeFinish.service.NoteService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/notes")
@AllArgsConstructor

public class NoteController {

    private NoteService service;
    private Map<String, Object> response;

    @GetMapping("/read")
public List<Note> readNoteDB(){
        return service.readNoteDB();
    }

    @GetMapping("/create")
    public ResponseEntity<?>  createNote(@RequestParam String NameNote, @RequestParam String TextNote, @RequestParam String email)
    {
        final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";

        if(Pattern.matches(EMAIL_REGEX, email)==false){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ошибка: указанный email: " + email + " не является email адресом");
        }else {
            service.createNote(NameNote,TextNote,email);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Заметка успешно создана");
        }

    }

    @PostMapping("/createFull")
    public ResponseEntity<?>  createNoteFull(@RequestBody NoteDTO noteDTO)
    {
         final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";

         if(Pattern.matches(EMAIL_REGEX, noteDTO.getEmail())==false){
             return ResponseEntity.status(HttpStatus.CONFLICT)
                     .body("Ошибка: указанный email: " + noteDTO.getEmail() + " не является email адресом");
         }else {
             service.createNoteFull(noteDTO);
             return ResponseEntity.status(HttpStatus.OK)
                     .body("Заметка успешно создана");
         }

    }


    @DeleteMapping("/deleteById/{idNote}")
    @Transactional
    public ResponseEntity<?> deleteByNote(@PathVariable Long idNote) {
       Note notesToDelete = service.findById(idNote);  // Находим объекты без удаления

        if (notesToDelete != null) {
            // Удаляем только если найден один объект
            service.deleteById(idNote);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Объект с ID " + idNote + " успешно удален.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Не найдено объектов с ID: " + idNote);
        }
    }

    @PutMapping("/update/{idNote}")
    public ResponseEntity<?> updateNote(@PathVariable Long idNote,@RequestBody NoteDTO noteDTO) {

        Note updNote = service.findById(idNote);
        if(updNote==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Не найдено объектов с ID: " + idNote);}
        else {service.updateNote(updNote,noteDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Объект с ID " + idNote + " успешно изменен.");
        }
    }

}

