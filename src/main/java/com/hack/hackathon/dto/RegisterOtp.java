package com.hack.hackathon.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterOtp {

    private String otp;
    private String firstName;
    private String email;
    private String password;
    private String number;

    private String role;

}
