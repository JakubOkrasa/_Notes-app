package com.jtm.notesapp.mappers;

import com.jtm.notesapp.commons.Mapper;
import com.jtm.notesapp.models.DTOs.NoteDto;
import com.jtm.notesapp.models.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper implements Mapper<Note, NoteDto> {

    @Override
    public NoteDto map(Note from) {
        return NoteDto.builder()
                .NoteTitle(from.getNoteTitle())
//                .NoteCategory(from.getNoteCategory())
                .NoteContent(from.getNoteContent())
                .userApp(from.getUserApp())
                .build();
    }

    @Override
    public Note reverseMap(NoteDto to) {
        return Note.builder()
                .noteTitle(to.getNoteTitle())
//                .noteCategory(to.getNoteCategory())
                .noteContent(to.getNoteContent())
                .userApp(to.getUserApp())
                .build();
    }
}
