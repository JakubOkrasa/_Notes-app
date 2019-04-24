package com.jtm.notesapp.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotesDto {
    @Column(name="note_title", nullable = false)
    private String NoteTitle;
    @Column(name="note_category")
    private String NoteCategory;
    @Column(name = "note_content", length=1024)
    private String NoteContent;
}
