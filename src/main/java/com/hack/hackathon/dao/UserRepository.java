package com.hack.hackathon.dao;


import com.hack.hackathon.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
  User findByEmail(String email);
  User findByNumber(String number);
 }
