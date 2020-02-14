package com.jtm.notesapp.services;

import com.jtm.notesapp.commons.security.UserAppRepository;
import com.jtm.notesapp.mappers.NoteMapper;
import com.jtm.notesapp.models.DTOs.NoteDto;
import com.jtm.notesapp.models.Note;
import com.jtm.notesapp.repositories.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService {
    private static final Logger logger = LoggerFactory.getLogger(NoteService.class);

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
        return noteRepository.findNotesByUserAppOrderByNoteModificationTimeDesc(userAppRepository
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
        SecurityContext securityContext = SecurityContextHolder.getContext();
        noteDto.setUserApp(userAppRepository
                .findUserAppByLogin(securityContext
                        .getAuthentication()
                        .getName())
                .orElseThrow(() -> new UsernameNotFoundException("Current user not found")));
        Note note = noteMapper.reverseMap(noteDto);
        note.setNoteModificationTime(Timestamp.valueOf(LocalDateTime.now()));
        return noteRepository.save(note);
    }

    public void updateNote(Note note) {
        try {
            noteRepository.findNotesById(note.getId());
            note.setNoteTitle(note.getNoteTitle());
            note.setNoteContent(note.getNoteContent());
            note.setNoteModificationTime(Timestamp.valueOf(LocalDateTime.now()));
            SecurityContext securityContext = SecurityContextHolder.getContext();
            note.setUserApp(userAppRepository
                    .findUserAppByLogin(securityContext.getAuthentication().getName())
                    .orElseThrow(() -> new UsernameNotFoundException("Error saving UserApp in updating note.")));
            noteRepository.save(note);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

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
    @PostFilter("filterObject.userApp.login == authentication.name")
    public List<Note> getNotesByTitle(String noteTitle) {
        SecurityContext sc = SecurityContextHolder.getContext();
        logger.info("current ROLE in NoteService: " + sc.getAuthentication().getAuthorities().toString());
        return noteRepository.findNotesByNoteTitleContainingIgnoreCase(noteTitle);
    }

    public void deleteNotesByNoteTitle(String noteTitle) { noteRepository.deleteByNoteTitle(noteTitle); }






}
