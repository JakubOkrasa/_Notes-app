package com.jtm.notesapp.services;

import com.jtm.notesapp.commons.security.UserAppRepository;
import com.jtm.notesapp.models.UserApp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAppService {

    UserAppRepository userAppRepository;

    public UserAppService(UserAppRepository userAppRepository) {
        this.userAppRepository = userAppRepository;
    }

    public UserApp addUser(UserApp userApp) {
//        userApp.
        return userAppRepository.save(userApp);
    }

    public List<UserApp> getUsers() {
        return userAppRepository.findAll();
    }
}
