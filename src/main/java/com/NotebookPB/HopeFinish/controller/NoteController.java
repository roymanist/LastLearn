package com.NotebookPB.HopeFinish.controller;

import com.NotebookPB.HopeFinish.model.Note;
import com.NotebookPB.HopeFinish.service.NoteService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, Object> createNoteFull(@RequestBody Note note)
    {
        response = new HashMap<>();
        response.put("message", "Сохранение успешно");
        response.put("notes", service.createNoteFull(note));
        return response;
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
