package com.jtm.notesapp.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAppDto {
    @Size(min=2, max=10)
    private String login;

    @Size(min=4, max=16)
    private String password;
}
