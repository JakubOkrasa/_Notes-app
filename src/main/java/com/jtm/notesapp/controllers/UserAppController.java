package com.jtm.notesapp.controllers;

import com.jtm.notesapp.commons.LoginExistsException;
import com.jtm.notesapp.models.DTOs.UserAppDto;
import com.jtm.notesapp.repositories.UserAppRepository;
import com.jtm.notesapp.models.UserApp;
import com.jtm.notesapp.services.UserAppService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class UserAppController {

    private UserAppService userAppService;

    public UserAppController(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @PostMapping("/users")
    UserApp addUser(@RequestBody UserAppDto userApp) throws LoginExistsException {
        return userAppService.addUser(userApp);
    }

    @GetMapping("/users")
    List<UserApp> getUsers() {
        return userAppService.getUsers();
    }
}
