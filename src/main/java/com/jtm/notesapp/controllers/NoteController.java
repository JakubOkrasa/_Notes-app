package com.jtm.notesapp.controllers;

import com.jtm.notesapp.models.DTOs.NoteDto;
import com.jtm.notesapp.models.Note;
import com.jtm.notesapp.services.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin //"żeby nie mieć problemów z angularem w przyszłości
@RequestMapping("/api/v1")
public class NoteController {
    private NoteService noteService;

    NoteController(NoteService noteService) {
        this.noteService = noteService;
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
        return noteService.getNotes();
    }


    //==============================
    //
    //
    //          DTO
    //
    //
    //==============================

    //@GetMapping("/dto/notes")
    //public List<NoteDto> getNotesDto() {
    //    return noteService.getNotesDto();
    //}


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
            return noteService.getNotesDtoByTitle(notetitle);
        }
        return  noteService.getNotesDto();
    }


}