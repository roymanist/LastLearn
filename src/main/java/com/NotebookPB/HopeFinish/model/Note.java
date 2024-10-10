package com.NotebookPB.HopeFinish.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notebook")
public class Note {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long id;
    private String name;
    private String text;
    private String email;
    private LocalDateTime dateCreate;
    private LocalDateTime  dateLastChange;


}

