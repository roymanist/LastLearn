package com.NotebookPB.HopeFinish.repository;

import com.NotebookPB.HopeFinish.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note,Long> {
    List<Note> deleteByName(String name);
}
