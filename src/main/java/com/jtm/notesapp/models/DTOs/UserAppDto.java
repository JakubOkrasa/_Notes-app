package com.jtm.notesapp.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAppDto {
    @Size(min=2, max=10, message = "Login must contain between 2 and 10 characters.")
    private String login;

    @Size(min=4, max=16, message = "Password must contain between 4 and 16 characters.")
    private String password;
}
