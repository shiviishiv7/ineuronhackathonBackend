package com.hack.hackathon.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductCountMap {

    private String id;
    private String userId;
    private String productId;
    private int quantity;



}
