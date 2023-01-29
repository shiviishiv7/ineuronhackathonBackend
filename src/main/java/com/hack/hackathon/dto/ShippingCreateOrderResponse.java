package com.hack.hackathon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ShippingCreateOrderResponse {
    private String order_id;
    private String shipment_id;
    private String status;
    private Integer status_code;
}
