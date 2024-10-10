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
    public List<Note> createNote(@RequestParam String NameNote, @RequestParam String TextNote)
    {
        return service.createNote(NameNote,TextNote);
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


    @DeleteMapping("/deleteByName/{nameNote}")
    @Transactional
    public ResponseEntity<?> deleteByNote(@PathVariable String nameNote) {
        List<Note> notesToDelete = service.findByName(nameNote);  // Находим объекты без удаления

        if (notesToDelete.size() > 1) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ошибка: найдено больше одного объекта с названием: " + nameNote + ". Удаление заблокировано.");
        } else if (notesToDelete.size() == 1) {
            // Удаляем только если найден один объект
            service.deleteByName(nameNote);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Объект с названием " + nameNote + " успешно удален.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Не найдено объектов с названием: " + nameNote);
        }
    }


}

