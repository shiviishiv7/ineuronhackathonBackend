package com.hack.hackathon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public  class AddressDto  {

    private String id;
    private Integer userId;
    private String firstName;
    private String lastName;
//    private String streetAddress;
    private String landmark;
    private String state;
    private   String townorcity;
    private   String postcodezip;
    private   String country;
    private   String email;
    private    String phoneno;

}
