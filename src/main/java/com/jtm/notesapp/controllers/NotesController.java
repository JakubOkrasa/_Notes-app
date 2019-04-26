package com.jtm.notesapp.controllers;

import com.jtm.notesapp.models.DTOs.NotesDto;
import com.jtm.notesapp.models.Notes;
import com.jtm.notesapp.services.NotesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin //"żeby nie mieć problemów z angularem w przyszłości
@RequestMapping("/api/v1")
public class NotesController {
    private NotesService notesService;

    NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    //==============================
    //
    //
    //          DAO
    //
    //
    //==============================

    @GetMapping("/notes")
    public List<Notes> getNotes() {
        return notesService.getNotes();
    }


    //==============================
    //
    //
    //          DTO
    //
    //
    //==============================

    //@GetMapping("/dto/notes")
    //public List<NotesDto> getNotesDto() {
    //    return notesService.getNotesDto();
    //}

    @GetMapping("/dto/notes")
    public List<NotesDto> getNotesDtoByNoteTitle(@RequestParam(value = "notetitle", required = false) String notetitle) {
        if (notetitle != null) {
            return notesService.getNotesDtoByTitle(notetitle);
        }
        return  notesService.getNotesDto();
    }

}