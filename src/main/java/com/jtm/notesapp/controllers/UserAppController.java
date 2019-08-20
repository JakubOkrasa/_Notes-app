package com.jtm.notesapp.controllers;

import com.jtm.notesapp.commons.security.UserAppRepository;
import com.jtm.notesapp.models.DTOs.UserAppDto;
import com.jtm.notesapp.models.UserApp;
import com.jtm.notesapp.services.UserAppService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class UserAppController {

    UserAppService userAppService;
    UserAppRepository userAppRepository;

    public UserAppController(UserAppService userAppService, UserAppRepository userAppRepository) {
        this.userAppService = userAppService;
        this.userAppRepository = userAppRepository;
    }

//    @PostMapping("/users")
//    UserApp addUser(@RequestBody UserApp userApp) {
//        return userAppService.addUser(userApp);
//    }

    @GetMapping("/users")
    List<UserApp> getUsers() {
        return userAppService.getUsers();
    }
}
