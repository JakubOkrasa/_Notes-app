package com.jtm.notesapp.models.DTOs;

import com.jtm.notesapp.models.UserApp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteDto {
    private String NoteTitle;
    private String NoteCategory;
    private String NoteContent;
    private UserApp userApp;
}
