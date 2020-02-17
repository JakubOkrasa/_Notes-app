package com.jtm.notesapp.controllers;

import com.jtm.notesapp.mappers.NoteMapper;
import com.jtm.notesapp.models.DTOs.NoteDto;
import com.jtm.notesapp.models.Note;
import com.jtm.notesapp.services.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin //"żeby nie mieć problemów z angularem w przyszłości
@RequestMapping("/api/v1")
public class NoteController {
    private NoteService noteService;
    private NoteMapper noteMapper;

    public NoteController(NoteService noteService, NoteMapper noteMapper) {
        this.noteService = noteService;
        this.noteMapper = noteMapper;
    }

    //==============================
    //
    //
    //          DAO
    //
    //
    //==============================

    @GetMapping("/notes")
    public List<Note> getNotes() {
        return noteService.getNotesByUserApp();
    }


    //==============================
    //
    //
    //          DTO
    //
    //
    //==============================

    @GetMapping("/dto/notes")
    public List<NoteDto> getNotesDto() {
        return noteService.getNotesDto();
    }


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
    public List<NoteDto> getNotesDtoByNoteTitle(@RequestParam(value = "noteTitle") String noteTitle) {
        return noteService.getNotesByTitle(noteTitle)
                .stream()
                .map(n -> noteMapper.map(n)).collect(Collectors.toList());
    }


}