package com.jtm.notesapp.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAppDto {
    @NotEmpty
    private String login;

    @NotEmpty
    private String password;
}
