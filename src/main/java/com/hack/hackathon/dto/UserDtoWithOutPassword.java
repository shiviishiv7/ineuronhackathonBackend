package com.hack.hackathon.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDtoWithOutPassword {
    private String token;
    private String id;
    private String firstName;
    private String email;
    private String role;
    private String number;
}
