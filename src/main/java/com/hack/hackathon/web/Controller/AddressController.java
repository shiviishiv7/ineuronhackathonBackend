package com.hack.hackathon.web.Controller;


import com.hack.hackathon.exceptionsHandler.RecordNotFound;
import com.hack.hackathon.model.UserAddress;
import com.hack.hackathon.service.AddressService;
import com.hack.hackathon.web.response.AddressListResponse;
import com.hack.hackathon.web.response.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @GetMapping("/user/{userid}")
    public ResponseEntity<AddressListResponse> findAllAddressByUserId(@PathVariable("userid") String userid) {
        List<UserAddress> allAddressByUserId = addressService.findAllAddressByUserId(userid);
        return addStatusCodeAndMessageAddressList(allAddressByUserId, "success");
    }

    private ResponseEntity<AddressListResponse> addStatusCodeAndMessageAddressList(List<UserAddress> cartItemList, String message) {
        AddressListResponse productResponse = new AddressListResponse();
        productResponse.setData(cartItemList);
        productResponse.setMessage(message);
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<AddressListResponse>(productResponse, HttpStatus.OK);
    }

    private ResponseEntity<AddressResponse> addStatusCodeAndMessageUserAddress(UserAddress userAddress, String message) {
        AddressResponse productResponse = new AddressResponse();
        productResponse.setData(userAddress);
        productResponse.setMessage(message);
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<AddressResponse>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getAddressById(@PathVariable("id") String id) throws RecordNotFound {
        UserAddress addressByAddressId = addressService.findAddressByAddressId(id);
        return addStatusCodeAndMessageUserAddress(addressByAddressId, "success");
    }

    @PostMapping("/add")
    public ResponseEntity<AddressResponse> addAddress(@RequestBody UserAddress addressRequest) {
        UserAddress userAddress = addressService.addAddress(addressRequest);
        return addStatusCodeAndMessageUserAddress(userAddress, "Address Added Successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<AddressResponse> updateAddress(@RequestBody UserAddress address) throws RecordNotFound {
        UserAddress userAddress = addressService.updateAddressById(address);
        return addStatusCodeAndMessageUserAddress(userAddress, "Address Updated Successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<AddressResponse> deleteAddressById(@PathVariable("id") String id) throws RecordNotFound {
        addressService.deleteAddress(id);
        return addStatusCodeAndMessageUserAddress(null, "Address Deleted Successfully");
    }


}

