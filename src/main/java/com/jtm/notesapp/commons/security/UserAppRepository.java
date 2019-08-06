package com.jtm.notesapp.commons.security;

import com.jtm.notesapp.models.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAppRepository extends JpaRepository<UserApp, Integer> {
    Optional<UserApp> findUserAppByLogin(String login);
}
