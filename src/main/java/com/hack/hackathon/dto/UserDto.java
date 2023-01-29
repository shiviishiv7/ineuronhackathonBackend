package com.hack.hackathon.dto;

import com.hack.hackathon.util.UserState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {


    private String id;
    private String image;
    private String firstName;
    private String lastName;
    private String email;

    @Enumerated(EnumType.ORDINAL)
    private UserState role = UserState.ROLE_USER;
    private String number;

}