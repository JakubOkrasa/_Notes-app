package com.jtm.notesapp.controllers;

import com.jtm.notesapp.models.DTOs.NoteDto;
import com.jtm.notesapp.models.Note;
import com.jtm.notesapp.repositories.NoteRepository;
import com.jtm.notesapp.services.NoteService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    private NoteService noteService;
    private NoteRepository noteRepository;

    public HomeController(NoteService noteService, NoteRepository noteRepository) {
        this.noteService = noteService;
        this.noteRepository = noteRepository;
    }

    @GetMapping("/")
    public String getHomepage(Model model) {
        model.addAttribute("notes", noteService.getNotes());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        model.addAttribute("message", "you are logged in as " + securityContext.getAuthentication().getName());
        return "index";
    }

    @GetMapping("/add")
    public String getAddPage() {
        return "/add";
    }

    @PostMapping("/add")
    public String addNote(@ModelAttribute NoteDto note) {
        noteService.addNote(note);
        return "redirect:/";
    }

    @GetMapping("/view")
    public String viewNote(@RequestParam(value = "viewNoteId") Long id, Model model) {
        model.addAttribute("noteToView", noteService.getNoteById(id));
        return "view";
    }

    @GetMapping("/delete")
    public String deleteNote(@RequestParam(value = "delNoteId") Long id) {
        noteService.deleteNotesById(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable long id, Model model) {
        Note note = noteService.getNoteById(id);
        model.addAttribute("noteToEdit", note);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public  String updateNote(@PathVariable long id, @Valid Note note, BindingResult result, Model model) {
        if (result.hasErrors()) {
            note.setId(id);
            return "edit";
        }
        noteRepository.save(note);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


}
