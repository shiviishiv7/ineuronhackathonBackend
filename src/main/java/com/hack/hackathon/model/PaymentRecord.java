package com.hack.hackathon.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public  class PaymentRecord {

    @Id
    private String orderId;
    private String razorpayId;
    private String paymentId;
    private String razorpaySignature;
}