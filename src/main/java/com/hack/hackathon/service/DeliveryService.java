package com.hack.hackathon.service;


import com.hack.hackathon.dto.CreateReturnOrderBody;
import com.hack.hackathon.dto.ShippingCreateOrderResponse;
import com.hack.hackathon.dto.ShippingDto;
import com.hack.hackathon.dto.ShippingOrderDto;
import com.hack.hackathon.model.Shipping;
import com.hack.hackathon.web.response.ProductDeliveryReturnResponse;
import com.hack.hackathon.web.response.ShippingTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeliveryService {

    @Autowired
    private OrderService orderService;

//    public ShippingTokenResponse getShipRocketToken()
//    {
//        final String uri = "https://apiv2.shiprocket.in/v1/external/auth/login";
//
//        RestTemplate restTemplate = new RestTemplate();
//        LoginDto loginDto = new LoginDto("resume2carrier@gmail.com","tyuCVB78$#");
//        ShippingTokenResponse shippingTokenResponse = restTemplate.postForObject(uri, loginDto, ShippingTokenResponse.class);
//        return shippingTokenResponse;
//    }

    @Autowired
    private ShippingTokenResponse shippingTokenResponse;

    private HttpHeaders createHttpHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + shippingTokenResponse.getToken());
        return headers;
    }

    public ShippingOrderDto createOrder(ShippingDto shippingDto) {
        Shipping shipping = createShippingStatement(shippingDto);
        final String uri = "https://apiv2.shiprocket.in/v1/external/orders/create/adhoc";

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = createHttpHeaders();
            HttpEntity<Shipping> entity = new HttpEntity<Shipping>(shipping, headers);
            ResponseEntity<ShippingCreateOrderResponse> response = restTemplate.exchange(uri, HttpMethod.POST, entity, ShippingCreateOrderResponse.class);
            System.out.println("Result - status (" + response.getStatusCode() + ") has body: " + response.hasBody());

            ShippingCreateOrderResponse body = response.getBody();
            this.orderService.updateOrder(shippingDto.getOrder_id(),body.getOrder_id(),body.getShipment_id());
        } catch (Exception eek) {
            System.out.println("** Exception: " + eek.getMessage());
        }
        return null;
    }

    private Shipping createShippingStatement(ShippingDto shippingDto) {
        Shipping shipping = new Shipping();
        shipping.setOrder_id(shippingDto.getOrder_id());
        shipping.setOrder_date(shipping.getOrder_date());
        shipping.setBilling_customer_name(shippingDto.getBilling_customer_name());
        shipping.setBilling_last_name(shippingDto.getBilling_last_name());
        shipping.setBilling_address(shippingDto.getBilling_address());
        shipping.setBilling_city(shippingDto.getBilling_city());
        shipping.setBilling_pincode(shippingDto.getBilling_pincode());
        shipping.setBilling_email(shippingDto.getBilling_email());
        shipping.setShipping_phone(shippingDto.getBilling_phone());
        shipping.setShipping_state(shippingDto.getBilling_state());
        shipping.setShipping_country(shippingDto.getBilling_country());
        shipping.setOrder_items(shippingDto.getOrder_items());
        shipping.setSub_total(shippingDto.getSub_total());
        shipping.setLength(shippingDto.getLength());
        shipping.setWeight(shippingDto.getWeight());
        shipping.setHeight(shippingDto.getHeight());
        shipping.setBreadth(shippingDto.getBreadth());
        return shipping;

    }

    public Object getTrackingDetailsById(String orderId) {


        final String uri = "https://apiv2.shiprocket.in/v1/external/courier/track/awb/" + orderId;

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = createHttpHeaders();
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<Object> response = restTemplate.exchange(uri, HttpMethod.GET, entity, Object.class);
            System.out.println("Result - status (" + response.getStatusCode() + ") has body: " + response.hasBody());
            return response.getBody();
        } catch (Exception eek) {
            System.out.println("** Exception: " + eek.getMessage());
        }

        return null;

    }

    public ProductDeliveryReturnResponse createReturnOrder(CreateReturnOrderBody body) {


        final String uri = "https://apiv2.shiprocket.in/v1/external/orders/create/return";

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = createHttpHeaders();
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            return restTemplate.postForObject(uri, body, ProductDeliveryReturnResponse.class);

        } catch (Exception eek) {
            System.out.println("** Exception: " + eek.getMessage());
        }

        return null;
    }


//    public

}
