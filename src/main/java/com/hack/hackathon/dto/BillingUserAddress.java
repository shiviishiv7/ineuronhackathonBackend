package com.hack.hackathon.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillingUserAddress {
    private String billing_customer_name;
    private String billing_last_name;
    private String       billing_address;
    private String       billing_address_2;
    private String         billing_city;
    private String         billing_pincode;
    private String          billing_state;
    private String         billing_country;
    private String           billing_email;
    private String           billing_phone;
}
