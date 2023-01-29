package com.hack.hackathon.web.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.hack.hackathon.model.UserAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressResponse {
    private String message;
    private Integer statusCode;
    private long timeStamp;
    private UserAddress data;
}