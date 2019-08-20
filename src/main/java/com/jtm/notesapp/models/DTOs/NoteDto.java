package com.jtm.notesapp.models.DTOs;

import com.jtm.notesapp.models.UserApp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteDto {
    @Size(min=1, max=15, message = "Title must contain from 1 to 15 characters.")
    private String NoteTitle;

    @Size(min=3, max=15, message = "Category must contain from 3 to 15 characters.")
    private String NoteCategory;

    @Size(min=1, max=1024, message = "Content must contain from 1 to 1024 characters.")
    private String NoteContent;

    private UserApp userApp;
}
