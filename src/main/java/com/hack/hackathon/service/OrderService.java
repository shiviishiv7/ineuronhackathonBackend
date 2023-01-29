package com.hack.hackathon.service;


import com.hack.hackathon.dao.OrderRepository;
import com.hack.hackathon.dao.PaymentRecordRepository;
import com.hack.hackathon.dto.OrdersDto;
import com.hack.hackathon.exceptionsHandler.RecordNotFound;
import com.hack.hackathon.model.Orders;
import com.hack.hackathon.model.PaymentRecord;
import com.mongodb.client.result.UpdateResult;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PaymentRecordRepository paymentRecordRepository;

    @Autowired
    OrderRepository orderRepository;


    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private RazorpayClient razorpayClient;

    public void updateOrder(String orderId,String shipRocketId,String shippingId) throws RecordNotFound {

		boolean byIsActiveAndId = orderRepository.existsById(orderId);
		if(!byIsActiveAndId){
			throw new RecordNotFound("Order ID Does not exists "+orderId);
		}
		Query query = new Query().addCriteria(Criteria.where("id").is(orderId));
//		Update updateDefination = new Update().set("isActive",false);
		Update update = new Update();
		update.set("trackingId",shipRocketId);
		update.set("shippmentId",shippingId);

		UpdateResult updateResult = mongoTemplate.upsert(query,update,Orders.class);
		System.out.println("category item deleted"+orderId+"\t"+updateResult.getMatchedCount());


    }

    public ResponseEntity<Orders> saveOrdersToDB(Orders orderRequest) {
        Orders save = orderRepository.save(orderRequest);
        return ResponseEntity.ok(save);
    }


//	public OrderAndSellerDetailResponse verifyOrder(String orderId) {
//		OrderAndSellerDetailResponse orderDtl = null;
//		return orderDtl;
//	}


    public List<Orders> getAllProductForFarmer(String userid) {

        List<Orders> paid = orderRepository.findAllProductByStatusAndFarmerId("PAID", userid);
        return paid;
    }

    public Orders getOrderById(String id) throws Exception {
//		Orders orders = orderRepository.findByIsActiveAndId(true,id);
//		if(orders==null){
//			throw new RecordNotFound("Address Not found By Id "+id);
//		}else return orders;
        return null;
    }

    public String addOrder(@RequestBody OrdersDto orderRequest) throws RazorpayException {

        Orders orders = new Orders();
        orders.setAmount(orderRequest.getAmount());
        orders.setAddressId(orderRequest.getAddressId());
        orders.setQuantity(orderRequest.getQuantity());
        orders.setFarmerId(orderRequest.getFarmerId());
        orders.setStatus(orderRequest.getUserId());
        orders.setProductId(orderRequest.getProductId());
        orders.setUserId(orderRequest.getUserId());

         this.orderRepository.save(orders);
        return "Order has been created";
     // he has told to not includ payment

//        JSONObject options = new JSONObject();
//        options.put("amount", orders.getAmount() * 100);
//        options.put("currency", "INR");
//        options.put("receipt", "txn_123456");
//        Order order = razorpayClient.Orders.create(options);
//        System.out.println(order);
//        orders.setId(order.get("id"));
//		Orders map = new Orders(max+100,orderRequest.getAmount(),orderRequest.getOrderState(),orderRequest.getUserId(),
//				orderRequest.getAddressId(),orderRequest.getSellerAmount(),orderRequest.getOrderProductCountMapList());
//        Orders save = orderRepository.save(orders);
//        return save;
    }

    public boolean deleteOrderById(String id) throws RecordNotFound {
//		Orders byIsActiveAndId = orderRepository.findByIsActiveAndId(true, id);
//		if(byIsActiveAndId==null){
//			throw new RecordNotFound("Order ID Does not exists "+id);
//		}
//		Query query = new Query().addCriteria(Criteria.where("id").is(id));
////		Update updateDefination = new Update().set("isActive",false);
//		Update update = new Update();
//		update.set("isActive",false);
//		update.set("deleted",true);
//
//		UpdateResult updateResult = mongoTemplate.upsert(query,update,Orders.class);
//		System.out.println("category item deleted"+id+"\t"+updateResult.getMatchedCount());
        return true;
    }

    public String updatePaymentorder(PaymentRecord paidDto) throws RecordNotFound {
        		boolean byIsActive = orderRepository.existsById(paidDto.getOrderId());
		if(!byIsActive){
			throw new RecordNotFound("Order ID Does not exists "+paidDto.getOrderId());
		}
		Query query = new Query().addCriteria(Criteria.where("id").is(paidDto.getOrderId()));
//		Update updateDefination = new Update().set("isActive",false);
		Update update = new Update();
		update.set("status","PAID");


		UpdateResult updateResult = mongoTemplate.upsert(query,update,Orders.class);
        paymentRecordRepository.save(paidDto);
        return "Order successfull placed";

    }

    public List<Orders> getAllProductForUserWhoAreSuccessfullyPaid(String userid) {
        return null;
    }
}
