package com.jtm.notesapp.commons.security;

import com.jtm.notesapp.models.DTOs.UserAppDto;
import com.jtm.notesapp.services.UserAppService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private UserAppService userAppService;

    public RegistrationController(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        UserAppDto userAppDto = new UserAppDto();
        model.addAttribute("user", userAppDto);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserAppDto userAppDto) {
        userAppService.addUser(userAppDto);
        return "redirect:/";
    }
}
