package com.jtm.notesapp.services;

import com.jtm.notesapp.models.Notes;
import com.jtm.notesapp.repositories.NotesRepository;
import com.sun.tools.javac.resources.CompilerProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    private NotesRepository notesRepository;

    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    /*
     *
     * DAO - Data Access Object
     *
     */
    public List<Notes> getNotes() {
        return notesRepository.findAll();
    }

}
