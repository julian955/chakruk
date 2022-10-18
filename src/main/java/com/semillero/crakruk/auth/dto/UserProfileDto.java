package com.semillero.crakruk.auth.dto;


import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserProfileDto {

    private String user;
    @Email
    private String email;
    private String photo;
}