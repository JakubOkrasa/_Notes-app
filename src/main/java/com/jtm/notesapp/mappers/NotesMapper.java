package com.jtm.notesapp.mappers;

import com.jtm.notesapp.commons.Mapper;
import com.jtm.notesapp.models.DTOs.NotesDto;
import com.jtm.notesapp.models.Notes;
import org.springframework.stereotype.Component;

@Component
public class NotesMapper implements Mapper<Notes, NotesDto> {

    @Override
    public NotesDto map(Notes from) {
        return NotesDto.builder()
                .NoteTitle(from.getNoteTitle())
                .NoteCategory(from.getNoteCategory())
                .NoteContent(from.getNoteContent())
                .build();
    }

    @Override
    public Notes reverseMap(NotesDto to) {
        return Notes.builder()
                .NoteTitle(to.getNoteTitle())
                .NoteCategory(to.getNoteCategory())
                .NoteContent(to.getNoteContent())
                .build();
    }
}
