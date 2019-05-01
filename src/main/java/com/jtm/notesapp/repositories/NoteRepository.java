package com.jtm.notesapp.repositories;

import com.jtm.notesapp.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    Optional<Note> findNotesByNoteTitleContainingIgnoreCase(String noteTitle);

    //my version
    //List<Note> findNotesByNoteTitleContainingIgnoreCase(String noteTitle);

    @Transactional
    @Modifying
    void deleteByNoteTitleIgnoreCase(String noteTitle);

    //Note findNotesById(Long noteID);

}
