package com.hack.hackathon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private int quantity;
    private Double price;
    private List<String> imageList;

}

