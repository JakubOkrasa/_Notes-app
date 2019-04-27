package com.jtm.notesapp.services;

import com.jtm.notesapp.mappers.NotesMapper;
import com.jtm.notesapp.models.DTOs.NotesDto;
import com.jtm.notesapp.models.Notes;
import com.jtm.notesapp.repositories.NotesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public Notes addNote(NotesDto notesDto) {
        return notesRepository.save(notesMapper.reverseMap(notesDto));
    }

    public void updateNoteByNoteTitle(NotesDto notesDto) {
        notesRepository.findNotesByNoteTitleContainingIgnoreCase(notesDto.getNoteTitle())
                .ifPresent(n -> {
                    n.setNoteCategory(notesDto.getNoteCategory());
                    n.setNoteContent(notesDto.getNoteContent());
                    n.setNoteTitle(notesDto.getNoteTitle());

                    notesRepository.save(n);
                });
    }

    public List<NotesDto> getNotesDtoByTitle(String noteTitle) {
        List<Notes> notesList = new ArrayList<>();
//        List<NotesDto> notesDtoList = new ArrayList<>();
        notesRepository
                .findNotesByNoteTitleContainingIgnoreCase(noteTitle)
                .ifPresent(n -> notesList.add(n));
        return notesList.stream()
                .map(n -> notesMapper.map(n))
                .collect(Collectors.toList());
    }

    public void deleteNotesByTitle(String noteTitle) {
        notesRepository.deleteByNoteTitleIgnoreCase(noteTitle);
    }
}
