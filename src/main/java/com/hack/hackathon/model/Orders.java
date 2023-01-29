package com.hack.hackathon.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document
public class Orders  {

	@Id
	private String id;
	double amount;

	private String userId;
	private String farmerId;
	private String addressId;
	private String productId;

	private Integer quantity;
	private String status;
	//shipp rocket order_id
	private String trackingId;
	private String shippmentId;


	private LocalDateTime createdAt;

}
