package com.jtm.notesapp.controllers;

import com.jtm.notesapp.models.DTOs.NoteDto;
import com.jtm.notesapp.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private NoteService noteService;

    public HomeController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String getHomepage(Model model) {
        model.addAttribute("notes", noteService.getNotesDto());
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

    @GetMapping("/delete")
    public String deleteNote(@RequestParam(value = "delnote") String noteTitle) {
        noteService.deleteNotesByTitle(noteTitle);
        System.out.println("Home: deleted =====================");
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String getEditPage() {
        return "redirect:/";
    }

    @PostMapping("/edit")
    public  String editNote(@ModelAttribute NoteDto editedNote) {
        noteService.updateNoteByNoteTitle(editedNote);
        return "edit";
    }
}
