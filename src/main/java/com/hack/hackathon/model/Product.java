package com.hack.hackathon.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Product  {

    @Id
    private String id;
    private String userId;
    private String name;
    private String description;
    private int quantity;
    private Double price;
    private List<String> imageList;


}
