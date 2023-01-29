package com.hack.hackathon.dao;


import com.hack.hackathon.model.UserAddress;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AddressRepository extends MongoRepository<UserAddress,String> {
    List<UserAddress> findAllAddressByUserId(String userId);

}
