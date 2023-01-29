package com.hack.hackathon.service;

import com.hack.hackathon.dao.ProductRepository;
import com.hack.hackathon.exceptionsHandler.RecordNotFound;
import com.hack.hackathon.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(String id) throws RecordNotFound {

        Optional<Product> byIsActiveAndId = productRepository.findById(id);
        if(byIsActiveAndId.isEmpty()){
            throw new RecordNotFound("Product not found By "+id);
        }
        return byIsActiveAndId.get();

    }
    public void updateProductById(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAllProduct() {	return productRepository.findAll();}

    public Product addProduct(Product productRequest) {
        Product save = productRepository.save(productRequest);
        return save;
    }

    public Product updateProduct(Product product) {
        Product save = productRepository.save(product);
        return save;
    }

    public boolean deleteProduct(String id) throws RecordNotFound {
        boolean byIsActiveAndId = productRepository.existsById(id);
        if(!byIsActiveAndId){
            throw new RecordNotFound("Product not exists by Id "+id);
        }
         this.productRepository.deleteById(id);
        return true;
    }

    public List<Product> searchProduct(String value, Integer minprice, Integer maxprice) {

        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();
        if (value != null && !value.isBlank()) {
            criteriaList.add(Criteria.where("name").regex(value));
            criteriaList.add(Criteria.where("description").regex(value));

        }
        if(minprice!=null && minprice>10){
            criteriaList.add(Criteria.where("price").gte(minprice));
        }
        if(maxprice!=null && maxprice<10000){
            criteriaList.add(Criteria.where("price").lte(maxprice));
        }

        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().orOperator(criteriaList.toArray(new Criteria[0])));
        }
        List<Product> productList = mongoTemplate.find(query, Product.class);
        return productList;

    }


    public List<Product> findAllProductByUserId(String id) {
        return productRepository.findAllProductByUserId(id);
    }
}

