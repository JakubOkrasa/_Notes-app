package com.jtm.notesapp.controllers;

import com.jtm.notesapp.commons.LoginExistsException;
import com.jtm.notesapp.models.DTOs.UserAppDto;
import com.jtm.notesapp.models.UserApp;
import com.jtm.notesapp.services.UserAppService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
    public ModelAndView registerUser(@ModelAttribute("user") @Valid UserAppDto userAppDto,
                                     BindingResult result, WebRequest request, Errors errors) {
        UserApp registered = new UserApp();

        if(!result.hasErrors()) {
            registered= createUser(userAppDto, result);
        }
        if (registered == null) {
            result.rejectValue("login", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("register", "user", userAppDto);
        }
        else {
            return new ModelAndView("login", "user", userAppDto);
        }
    }

    private UserApp createUser(UserAppDto userAppDto, BindingResult result) {
        UserApp registered = null;
        try {
            registered = userAppService.addUser(userAppDto);
        }
        catch (LoginExistsException e) {
            return null;
        }
        return registered;
    }
}
