package com.hack.hackathon.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class UserAddress  {

    @Id
    private String id;
    private String userId;
    private String firstName;
   private String lastName;
   private String streetAddress;
   private String landmark;
   private String state;
  private   String townorcity;
  private   String postcodezip;
  private   String country;
  private   String email;
  private    String phoneno;


}
