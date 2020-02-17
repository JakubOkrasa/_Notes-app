package com.jtm.notesapp.controllers;

import com.jtm.notesapp.mappers.NoteMapper;
import com.jtm.notesapp.models.DTOs.NoteDto;
import com.jtm.notesapp.models.Note;
import com.jtm.notesapp.services.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class NoteController {
    private NoteService noteService;
    private NoteMapper noteMapper;

    public NoteController(NoteService noteService, NoteMapper noteMapper) {
        this.noteService = noteService;
        this.noteMapper = noteMapper;
    }

    //==============================
    //          DAO
    //==============================

    @GetMapping("/notes")
    public List<Note> getNotes() {
        return noteService.getNotesByUserApp();
    }


    //==============================
    //          DTO
    //==============================

    @PostMapping("/dto/notes")
    Note addNote(@RequestBody NoteDto noteDto) {

        return noteService.addNote(noteDto);
    }

    @PutMapping("/dto/notes")
    void updateNote(@RequestBody NoteDto noteDto) {
        noteService.updateNoteByNoteTitle(noteDto);
    }


    @DeleteMapping("/dto/notes/{noteTitle}")
    void deleteNotesDtoByNoteTitle(@PathVariable String noteTitle) {
        noteService.deleteNotesByNoteTitle(noteTitle);
    }

    @GetMapping("/dto/notes")
    public List<NoteDto> getNotesDtoByNoteTitle(@RequestParam(value = "notetitle", required = false) String notetitle) {
        if (notetitle != null) {
            return noteService.getNotesByTitle(notetitle)
                    .stream()
                    .map(n -> noteMapper.map(n)).collect(Collectors.toList());
        }
        return  noteService.getNotesDto();
    }


}