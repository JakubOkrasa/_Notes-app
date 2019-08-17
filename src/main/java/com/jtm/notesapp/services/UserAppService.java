package com.jtm.notesapp.services;

import com.jtm.notesapp.commons.security.RoleRepository;
import com.jtm.notesapp.commons.security.UserAppRepository;
import com.jtm.notesapp.mappers.UserAppMapper;
import com.jtm.notesapp.models.DTOs.UserAppDto;
import com.jtm.notesapp.models.Role;
import com.jtm.notesapp.models.UserApp;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserAppService {


    private UserAppRepository userAppRepository;
    private UserAppMapper userAppMapper;
    private PasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;

    public UserAppService(UserAppRepository userAppRepository, UserAppMapper userAppMapper, PasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userAppRepository = userAppRepository;
        this.userAppMapper = userAppMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    public UserApp addUser(UserAppDto userAppDto) {
        System.out.printf("\n\nLOGIN: %s PASSWORD: %s\n", userAppDto.getLogin(), userAppDto.getPassword());
        UserApp userApp = userAppMapper.reverseMap(userAppDto);
        userApp.setPassword(bCryptPasswordEncoder.encode(userApp.getPassword()));
        userApp.setActive(1);
        userApp.setRoles(new HashSet<Role>(roleRepository.findByRole("USER")));
        return userAppRepository.save(userApp);
    }

    public List<UserApp> getUsers() {
        return userAppRepository.findAll();
    }

}
