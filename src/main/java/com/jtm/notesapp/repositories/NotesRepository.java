package com.jtm.notesapp.repositories;

import com.jtm.notesapp.models.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotesRepository extends JpaRepository<Notes, Long> {

    List<Notes> findNotesByNoteTitleContainingIgnoreCase(String noteTitle); //todo co daje optional zamiast List



}
