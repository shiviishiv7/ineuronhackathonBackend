package com.hack.hackathon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto  {

    double amount;

    private String userId;
    private String farmerId;
    private String addressId;
    private String productId;

    private Integer quantity;
    private String status;

}
