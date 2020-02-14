package com.jtm.notesapp.repositories;

import com.jtm.notesapp.models.Note;
import com.jtm.notesapp.models.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.access.prepost.PostFilter;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findNotesByNoteTitleContainingIgnoreCase(String noteTitle);

    Optional<Note> findNotesByNoteTitle(String noteTitle);



    //my version
    //List<Note> findNotesByNoteTitleContainingIgnoreCase(String noteTitle);

    @Transactional
    @Modifying
    void deleteById(Long id);

    @Transactional
    @Modifying
    void deleteByNoteTitle(String noteTitle);

    Note findNotesById(Long noteID);
    
    List<Note> findNotesByUserAppOrderByNoteModificationTimeDesc(UserApp userApp);

}
