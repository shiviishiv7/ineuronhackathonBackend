package com.hack.hackathon.web.Controller;


import com.hack.hackathon.dto.OrdersDto;
import com.hack.hackathon.exceptionsHandler.RecordNotFound;
import com.hack.hackathon.model.Orders;
import com.hack.hackathon.model.PaymentRecord;
import com.hack.hackathon.service.OrderService;
import com.hack.hackathon.web.response.OrderListResponse;
import com.hack.hackathon.web.response.OrderResponse;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrdersController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/user/seller/{userId}")
    //this api for farmner for and it will fetch all the product he has to deliver
    public ResponseEntity<OrderListResponse> getAllOrderByUserIdWhichAreSucess(@PathVariable("userId") String userid) {
        List<Orders> ordersList = orderService.getAllProductForFarmer(userid);
        return addStatusCodeAndMessageOrderList(ordersList);
    }


    @GetMapping("/user/{userId}")
    //this api for farmner for and it will fetch all the product he has to deliver
    public ResponseEntity<OrderListResponse> getAllProductForUser(@PathVariable("userId") String userid) {
        List<Orders> ordersList = orderService.getAllProductForUserWhoAreSuccessfullyPaid(userid);
        return addStatusCodeAndMessageOrderList(ordersList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable String id) throws Exception {
        Orders orders = orderService.getOrderById(id);
        return addStatusCodeAndMessageOrder(orders, "success");
    }

    @PostMapping("/success")
    public ResponseEntity<OrderResponse> orderPaid(@RequestBody PaymentRecord paidDto) throws RecordNotFound {
        String s = orderService.updatePaymentorder(paidDto);
        return addStatusCodeAndMessageOrder(null, s);
    }


    @PostMapping("/add")
    public ResponseEntity<OrderResponse> addOrder(@RequestBody OrdersDto orderRequest) throws RazorpayException {
        String orders = orderService.addOrder(orderRequest);
        return addStatusCodeAndMessageOrder(null, "Order successfully Placed");

    }

    private ResponseEntity<OrderListResponse> addStatusCodeAndMessageOrderList(List<Orders> ordersList) {

        OrderListResponse productResponse = new OrderListResponse();
        productResponse.setData(ordersList);
        productResponse.setMessage("success");
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<OrderListResponse>(productResponse, HttpStatus.OK);
    }

    private ResponseEntity<OrderResponse> addStatusCodeAndMessageOrder(Orders orders, String message) {

        OrderResponse productResponse = new OrderResponse();
        productResponse.setData(orders);
        productResponse.setMessage(message);
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<OrderResponse>(productResponse, HttpStatus.OK);
    }

//    @PutMapping("/update")
//    public ResponseEntity<OrderResponse> updateOrder(@RequestBody OrderStatusChange orderRequest) throws RecordNotFound {
//        orderService.updateOrder(orderRequest.getOrderId());
//        return addStatusCodeAndMessageOrder(null, "Successfully updated Order");
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponse> deleteOrderById(@PathVariable("id") String id) throws RecordNotFound {
        orderService.deleteOrderById(id);
        return addStatusCodeAndMessageOrder(null, "Successfully deleted order");
    }


}

