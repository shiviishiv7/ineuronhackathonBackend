package com.hack.hackathon.web.response;


import com.hack.hackathon.util.UserState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class JwtResponse {
    String token;
    private Integer userId;
    private String image;
    private String firstName;
    private String lastName;
    private String email;
    @Enumerated(EnumType.ORDINAL)
    private UserState role = UserState.ROLE_USER;
    private String number;

}