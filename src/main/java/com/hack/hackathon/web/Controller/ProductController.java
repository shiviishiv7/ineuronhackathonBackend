package com.hack.hackathon.web.Controller;


import com.hack.hackathon.exceptionsHandler.RecordNotFound;
import com.hack.hackathon.model.Product;
import com.hack.hackathon.service.ProductService;
import com.hack.hackathon.web.response.ProductListResponse;
import com.hack.hackathon.web.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    private ResponseEntity<ProductListResponse> addStatusCodeAndMessageProductList(List<Product> productList) {

        ProductListResponse productResponse = new ProductListResponse();
        productResponse.setData(productList);
        productResponse.setMessage("success");
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<ProductListResponse>(productResponse, HttpStatus.OK);
    }

    private ResponseEntity<ProductResponse> addStatusCodeAndMessageProduct(Product productList, String message) {

        ProductResponse productResponse = new ProductResponse();
        productResponse.setData(productList);
        productResponse.setMessage(message);
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody Product productRequest) {
        Product product = productService.addProduct(productRequest);
        return addStatusCodeAndMessageProduct(product, "Product added successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody Product product) {
        Product product1 = productService.updateProduct(product);
        return addStatusCodeAndMessageProduct(product1, "Product updated successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> deleteProductById(@PathVariable("id") String id) throws RecordNotFound {
        boolean productBoolean = productService.deleteProduct(id);
        return addStatusCodeAndMessageProduct(null, "Product deleted Successfully");
    }

    //this api belong to farmer dashboard
    @GetMapping("/user/{userId}")
    public ResponseEntity<ProductListResponse> getAllProductByUserId(@PathVariable("userId") String id) throws RecordNotFound {
        List<Product> productList = productService.findAllProductByUserId(id);
        return addStatusCodeAndMessageProductList(productList);
    }


}
