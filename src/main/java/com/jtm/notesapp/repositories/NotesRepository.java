package com.jtm.notesapp.repositories;

import com.jtm.notesapp.models.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Notes, Long> {

}
