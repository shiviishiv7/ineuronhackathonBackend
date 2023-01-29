package com.hack.hackathon.dao;

import com.hack.hackathon.model.PaymentRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRecordRepository extends MongoRepository<PaymentRecord,String> {
}
