package com.hack.hackathon.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShippingDto {
    private String   order_id;
    private String   order_date;


private String billing_customer_name;
    private String billing_last_name;
    private String       billing_address;
    private String         billing_city;
    private String         billing_pincode;
    private String          billing_state;
    private String         billing_country;
    private String           billing_email;
    private String           billing_phone;
    private List<ShippingOrderDto> order_items;

    private Double      sub_total ;
    private Double      length ;
    private Double      breadth ;
    private Double       height ;
    private Double      weight ;
}
