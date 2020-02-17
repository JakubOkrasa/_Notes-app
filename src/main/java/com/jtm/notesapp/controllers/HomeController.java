package com.jtm.notesapp.controllers;

import com.jtm.notesapp.models.DTOs.NoteDto;
import com.jtm.notesapp.models.Note;
import com.jtm.notesapp.services.NoteService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    private NoteService noteService;

    public HomeController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String getHomepage(Model model) {
        addUserSpecificDataToModel(model);
        return "index";
    }

    @PostMapping("/add")
    public String addNote(@ModelAttribute("note") @Valid NoteDto noteDto) {
        noteService.addNote(noteDto);
        return "redirect:/";
    }

    @GetMapping("/view-modal")
    @ResponseBody
    public Note viewNote(@RequestParam Long id) {
        return noteService.getNoteById(id);
    }

    @GetMapping("/delete")
    public String deleteNote(@RequestParam(value = "delNoteId") Long id) {
        noteService.deleteNotesById(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String showUpdateForm(@RequestParam(value = "editNoteId") Long id, Model model) {
        model.addAttribute("noteToEdit", noteService.getNoteById(id));
        return "edit";
    }

    @PostMapping("/view-modal")
    public String updateNote(@RequestBody Note note) {
        noteService.updateNote(note);
        return "redirect:/"; //todo should be another view returned?

    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @GetMapping("/find")
    public String findNotesByTitle(@RequestParam(value = "searchingPhrase") String searchingPhrase, Model model) {
        addUserSpecificDataToModel(model);
        model.addAttribute("notes", noteService.getNotesByTitle(searchingPhrase));
        return "index";
    }

    private void addUserSpecificDataToModel(Model model) {
        model.addAttribute("notes", noteService.getNotesByUserApp());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        model.addAttribute("username", securityContext.getAuthentication().getName());
    }
}
