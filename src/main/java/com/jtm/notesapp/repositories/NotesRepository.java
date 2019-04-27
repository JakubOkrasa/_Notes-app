package com.jtm.notesapp.repositories;

import com.jtm.notesapp.models.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface NotesRepository extends JpaRepository<Notes, Long> {

    Optional<Notes> findNotesByNoteTitleContainingIgnoreCase(String noteTitle);

    //my version
    //List<Notes> findNotesByNoteTitleContainingIgnoreCase(String noteTitle);

    @Transactional
    @Modifying
    void deleteByNoteTitleIgnoreCase(String noteTitle);



}
