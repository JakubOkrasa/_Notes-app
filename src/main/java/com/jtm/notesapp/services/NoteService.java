package com.jtm.notesapp.services;

import com.jtm.notesapp.commons.security.UserAppRepository;
import com.jtm.notesapp.mappers.NoteMapper;
import com.jtm.notesapp.models.DTOs.NoteDto;
import com.jtm.notesapp.models.Note;
import com.jtm.notesapp.repositories.NoteRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private NoteRepository noteRepository;
    private NoteMapper noteMapper;
    private UserAppRepository userAppRepository;

    public NoteService(NoteRepository noteRepository, NoteMapper noteMapper, UserAppRepository userAppRepository) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
        this.userAppRepository = userAppRepository;
    }

    //==============================
    //          DAO
    //==============================
    public List<Note> getNotesByUserApp() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return noteRepository.findNotesByUserApp(userAppRepository
                .findUserAppByLogin(securityContext
                        .getAuthentication()
                        .getName())
                .orElseThrow(() -> new UsernameNotFoundException("Current User not found")));
    }

    public Note getNoteById(Long id) { return noteRepository.findNotesById(id); }

    public void deleteNotesById(Long id) { noteRepository.deleteById(id); }

    //==============================
    //          DTO
    //==============================

    public List<NoteDto> getNotesDto() {
        return noteRepository
                .findAll()
                .stream()
                .map(n -> noteMapper.map(n))
                .collect(Collectors.toList());
    }

    public Note addNote(NoteDto noteDto) {

        return noteRepository.save(noteMapper.reverseMap(noteDto));
    }

    public void updateNoteByNoteTitle(NoteDto noteDto) {
        noteRepository.findNotesByNoteTitle(noteDto.getNoteTitle())
                .ifPresent(n -> {
//                    n.setNoteCategory(noteDto.getNoteCategory());
                    n.setNoteContent(noteDto.getNoteContent());
                    n.setNoteTitle(noteDto.getNoteTitle());
                    noteRepository.save(n);
                });
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostFilter("filterObject.userApp == authentication.name")
    public List<NoteDto> getNotesDtoByTitle(String noteTitle) {
        List<Note> notesList = new ArrayList<>();
        noteRepository
                .findNotesByNoteTitleContainingIgnoreCase(noteTitle)
                .ifPresent(n -> notesList.add(n));
        return notesList.stream()
                .map(n -> noteMapper.map(n))
                .collect(Collectors.toList());
    }

    public void deleteNotesByNoteTitle(String noteTitle) { noteRepository.deleteByNoteTitle(noteTitle); }






}
