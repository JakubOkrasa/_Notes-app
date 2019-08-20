package com.jtm.notesapp.services;

import com.jtm.notesapp.commons.LoginExistsException;
import com.jtm.notesapp.commons.security.RoleRepository;
import com.jtm.notesapp.commons.security.UserAppRepository;
import com.jtm.notesapp.mappers.UserAppMapper;
import com.jtm.notesapp.models.DTOs.UserAppDto;
import com.jtm.notesapp.models.Role;
import com.jtm.notesapp.models.UserApp;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public UserApp addUser(UserAppDto userAppDto)
        throws LoginExistsException {

        if (loginExists(userAppDto.getLogin())) {
            throw new LoginExistsException(userAppDto.getLogin() + " login is already used.");
        }
        UserApp userApp = userAppMapper.reverseMap(userAppDto);
        userApp.setPassword(bCryptPasswordEncoder.encode(userApp.getPassword()));
        userApp.setActive(1);
        userApp.setRoles(new HashSet<Role>(roleRepository.findByRole("USER")));
        return userAppRepository.save(userApp);
    }

    private boolean loginExists(String login) {
        UserApp userApp = userAppRepository.findUserAppByLogin(login).orElse(null);
        if (userApp != null) {
            return true;
        }
        return false;
    }

    public List<UserApp> getUsers() {
        return userAppRepository.findAll();
    }

}
