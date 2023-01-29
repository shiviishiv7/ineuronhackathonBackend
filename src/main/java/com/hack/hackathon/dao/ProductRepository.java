package com.hack.hackathon.dao;


import com.hack.hackathon.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,String> {

    List<Product> findAllProductByUserId(String id);
}
