package com.jtm.notesapp.services;

import com.jtm.notesapp.commons.security.UserAppRepository;
import com.jtm.notesapp.mappers.UserAppMapper;
import com.jtm.notesapp.models.DTOs.UserAppDto;
import com.jtm.notesapp.models.UserApp;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserAppService {

    UserAppRepository userAppRepository;
    UserAppMapper userAppMapper;

    public UserAppService(UserAppRepository userAppRepository) {
        this.userAppRepository = userAppRepository;
    }

    public UserApp addUser(UserAppDto userAppDto) {
        UserApp userApp = userAppMapper.reverseMap(userAppDto);
        userApp.setActive(1);
//        userApp.setRoles(Collections.singleton("USER"));
        return userAppRepository.save(userApp);
    }

    public List<UserApp> getUsers() {
        return userAppRepository.findAll();
    }
}
