package com.jtm.notesapp.services;

import com.jtm.notesapp.mappers.NotesMapper;
import com.jtm.notesapp.models.DTOs.NotesDto;
import com.jtm.notesapp.models.Notes;
import com.jtm.notesapp.repositories.NotesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotesService {

    private NotesRepository notesRepository;
    private NotesMapper notesMapper;

    public NotesService(NotesRepository notesRepository, NotesMapper notesMapper) {
        this.notesRepository = notesRepository;
        this.notesMapper = notesMapper;
    }

    //==============================
    //
    //
    //          DAO
    //
    //
    //==============================
    public List<Notes> getNotes() {
        return notesRepository.findAll();
    }

    //==============================
    //
    //
    //          DTO
    //
    //
    //==============================

    public List<NotesDto> getNotesDto() {
        return notesRepository
                .findAll()
                .stream()
                .map(n -> notesMapper.map(n))
                .collect(Collectors.toList());
    }

    public List<NotesDto> getNotesDtoByTitle(String noteTitle) {
        return notesRepository
                .findNotesByNoteTitleContainingIgnoreCase(noteTitle)
                .stream()
                .map(n -> notesMapper.map(n))
                .collect(Collectors.toList());
    }

}
