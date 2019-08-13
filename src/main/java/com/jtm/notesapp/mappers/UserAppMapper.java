package com.jtm.notesapp.mappers;

import com.jtm.notesapp.commons.Mapper;
import com.jtm.notesapp.models.DTOs.UserAppDto;
import com.jtm.notesapp.models.Role;
import com.jtm.notesapp.models.UserApp;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class UserAppMapper implements Mapper<UserApp, UserAppDto> {
    @Override
    public UserAppDto map(UserApp from) {
        return UserAppDto.builder()
                .login(from.getLogin())
                .password(from.getPassword())
                .build();
    }

    @Override
    public UserApp reverseMap(UserAppDto to) {
//        insert into `user_role`(user_id, role_id) values (
        SecurityContext sc = SecurityContextHolder.getContext();
//        sc.getAuthentication().getPrincipal().toString().con
        return UserApp.builder()
                .login(to.getLogin())
                .password(to.getPassword())
//                .roles(new HashSet<Role>(Collections.singleton(
//                        new Role()
//                        new SimpleGrantedAuthority("ADMIN"))))
//                .roles(Arrays.asList("ROLE_USER")
                .build();
    }
}
