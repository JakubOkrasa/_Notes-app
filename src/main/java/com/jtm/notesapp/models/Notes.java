package com.jtm.notesapp.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "notes")
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="note_title", nullable = false)
    private String NoteTitle;

    @Column(name="note_category")
    private String NoteCategory;

    @Column(name = "note_content", length=1024)
    private String NoteContent;
}
