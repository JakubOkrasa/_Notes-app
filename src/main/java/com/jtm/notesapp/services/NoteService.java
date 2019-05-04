package com.jtm.notesapp.services;

import com.jtm.notesapp.mappers.NoteMapper;
import com.jtm.notesapp.models.DTOs.NoteDto;
import com.jtm.notesapp.models.Note;
import com.jtm.notesapp.repositories.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private NoteRepository noteRepository;
    private NoteMapper noteMapper;

    public NoteService(NoteRepository noteRepository, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    //==============================
    //          DAO
    //==============================
    public List<Note> getNotes() {
        return noteRepository.findAll();
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
        noteRepository.findNotesByNoteTitleContainingIgnoreCase(noteDto.getNoteTitle())
                .ifPresent(n -> {
                    n.setNoteCategory(noteDto.getNoteCategory());
                    n.setNoteContent(noteDto.getNoteContent());
                    n.setNoteTitle(noteDto.getNoteTitle());

                    noteRepository.save(n);
                });
    }

    public List<NoteDto> getNotesDtoByTitle(String noteTitle) {
        List<Note> notesList = new ArrayList<>();
//        List<NoteDto> notesDtoList = new ArrayList<>();
        noteRepository
                .findNotesByNoteTitleContainingIgnoreCase(noteTitle)
                .ifPresent(n -> notesList.add(n));
        return notesList.stream()
                .map(n -> noteMapper.map(n))
                .collect(Collectors.toList());
    }

    public void deleteNotesByNoteTitle(String noteTitle) { noteRepository.deleteByNoteTitle(noteTitle); }





}
