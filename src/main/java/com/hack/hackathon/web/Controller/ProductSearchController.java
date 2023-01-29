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

//@CrossOrigin(value = "http://localhost:4200/**")
@RestController
@RequestMapping("/api/v1/public/product")
public class ProductSearchController {
    @Autowired
    private ProductService productService;

    @GetMapping("/search")
    public ResponseEntity<ProductListResponse> search(@RequestParam(name = "value", required = false) String value,
                                                      @RequestParam(name = "min", required = false) Integer minprice,
                                                      @RequestParam(name = "max", required = false) Integer maxprice) {
        List<Product> products = productService.searchProduct(value, minprice, maxprice);
        return addStatusCodeAndMessageProductList(products);
    }


    @GetMapping("/all")
    public ResponseEntity<ProductListResponse> getAllProduct() {
        List<Product> products = productService.getAllProduct();
        return addStatusCodeAndMessageProductList(products);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") String id) throws RecordNotFound {
        Product productById = productService.getProductById(id);
        return addStatusCodeAndMessageProduct(productById, "success");
    }

    private ResponseEntity<ProductResponse> addStatusCodeAndMessageProduct(Product productList, String message) {

        ProductResponse productResponse = new ProductResponse();
        productResponse.setData(productList);
        productResponse.setMessage(message);
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }


    private ResponseEntity<ProductListResponse> addStatusCodeAndMessageProductList(List<Product> productList) {

        ProductListResponse productResponse = new ProductListResponse();
        productResponse.setData(productList);
        productResponse.setMessage("success");
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<ProductListResponse>(productResponse, HttpStatus.OK);
    }

}
