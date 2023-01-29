package com.hack.hackathon.web.Controller;


import com.hack.hackathon.dto.CreateReturnOrderBody;
import com.hack.hackathon.dto.ShippingDto;
import com.hack.hackathon.dto.ShippingOrderDto;
import com.hack.hackathon.service.DeliveryService;
import com.hack.hackathon.web.response.ProductDeliveryReturnResponse;
import com.hack.hackathon.web.response.ShippingTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public/shipment")
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private ShippingTokenResponse shippingTokenResponse;

//    @GetMapping("/")
//    public ShippingTokenResponse shippingTokenResponse(){
//        return shippingTokenResponse;
//    }

    @PostMapping("/add")
    public ShippingOrderDto shippingOrderDto(@RequestBody ShippingDto shippingDto) {
        return deliveryService.createOrder(shippingDto);
    }


    @GetMapping("/track")
    public Object shipmentTrackingResponse(@RequestParam("orderId") String orderId) {
        System.out.println(orderId);
        return deliveryService.getTrackingDetailsById(orderId);
    }

    @PostMapping("/return")
    public ProductDeliveryReturnResponse createReturnRequest(@RequestBody CreateReturnOrderBody createReturnOrderBody) {
        return this.deliveryService.createReturnOrder(createReturnOrderBody);
    }

}
