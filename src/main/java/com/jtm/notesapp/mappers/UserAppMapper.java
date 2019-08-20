package com.jtm.notesapp.mappers;

import com.jtm.notesapp.commons.Mapper;
import com.jtm.notesapp.models.DTOs.UserAppDto;
import com.jtm.notesapp.models.UserApp;
import org.springframework.stereotype.Component;


@Component
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
        return UserApp.builder()
                .login(to.getLogin())
                .password(to.getPassword())
                .build();
    }
}
