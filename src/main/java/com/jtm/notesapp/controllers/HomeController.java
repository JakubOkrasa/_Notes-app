package com.jtm.notesapp.controllers;

import com.jtm.notesapp.commons.security.UserAppRepository;
import com.jtm.notesapp.models.DTOs.NoteDto;
import com.jtm.notesapp.models.DTOs.UserAppDto;
import com.jtm.notesapp.models.Note;
import com.jtm.notesapp.models.UserApp;
import com.jtm.notesapp.repositories.NoteRepository;
import com.jtm.notesapp.services.NoteService;
import com.jtm.notesapp.services.UserAppService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Controller
public class HomeController {

    private NoteService noteService;
    private NoteRepository noteRepository;
    private UserAppRepository userAppRepository;

    public HomeController(NoteService noteService, NoteRepository noteRepository, UserAppRepository userAppRepository) {
        this.noteService = noteService;
        this.noteRepository = noteRepository;
        this.userAppRepository = userAppRepository;
    }

    @GetMapping("/")
    public String getHomepage(Model model) {
        model.addAttribute("notes", noteService.getNotesByUserApp());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        model.addAttribute("message", "you are logged in as " + securityContext.getAuthentication().getName());
        return "index";
    }

    @GetMapping("/add")
    public String getAddPage() {
        return "/add";
    }

    @PostMapping("/add")
    public String addNote(@ModelAttribute("note") @Valid NoteDto noteDto, BindingResult result, Errors errors) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        noteDto.setUserApp(userAppRepository
                .findUserAppByLogin(securityContext.getAuthentication().getName()).orElseThrow(() -> new UsernameNotFoundException("Current user not found")));
        noteService.addNote(noteDto);
//        if (result.hasErrors()) {
//            return new ModelAndView("index", "note", noteDto);
//        }
//        else {
//            return new ModelAndView("index", "note", noteDto);
//        }
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

    @GetMapping("/edit")
    public String showUpdateForm(@RequestParam(value = "editNoteId") Long id, Model model) {
        model.addAttribute("noteToEdit", noteService.getNoteById(id));
        return "edit";
    }

    @PostMapping("/update")
    public String updateNote(@RequestParam(value="updateNoteId") Long id, @Valid Note note, BindingResult result, Model model) {
        if (result.hasErrors()) {
            note.setId(id);
            return "edit";
        }
        SecurityContext securityContext = SecurityContextHolder.getContext();
        note.setUserApp(userAppRepository
                .findUserAppByLogin(securityContext.getAuthentication().getName()).orElseThrow(() -> new UsernameNotFoundException("Error saving UserApp in updating note.")));
        noteRepository.save(note);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/find")
    public String getFindPage() {
        return "/find";
    }

    @PostMapping("/find")
    public String findNotesByTitle(@RequestParam(value = "searchingPhrase") String searchingPhrase) {
        noteService.findNotesByTitle(searchingPhrase);
        return "found";
    }

}
