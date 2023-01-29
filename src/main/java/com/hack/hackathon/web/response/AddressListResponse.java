package com.hack.hackathon.web.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.hack.hackathon.model.UserAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressListResponse {
        private String message;
        private Integer statusCode;
        private long timeStamp;
        private List<UserAddress> data;
}

