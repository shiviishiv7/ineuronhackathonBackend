package com.hack.hackathon.dao;


import com.hack.hackathon.model.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Orders,String> {


//    Orders findByIsActiveAndId(boolean isActive, String id);
//    List<Orders> findByIsActive(boolean isActive);
//    List<Orders> findByIsActiveAndUserIdAndStatus(boolean isActive,String userId,String status);

    //find max
    Orders findTopByOrderByIdDesc();

    List<Orders> findAllProductByStatusAndFarmerId(String paid, String farmerId);

//	 void createOrder(OrderRequest orderRequest);
//
//   	 void updateOrderStatus(OrderStatusRequest orderstatusRequest);
//
//	 OrderAndSellerDetailResponse verifyOrder(String orderId);

//    @Query("{userId :?0}")                                                  //SQL Equivalent : SELECT * FROM BOOK WHERE ID=?
//    List<Orders> getAllOrderByUserId(Integer id);

}
