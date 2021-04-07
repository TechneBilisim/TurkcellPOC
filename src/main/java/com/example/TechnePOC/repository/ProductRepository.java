package com.example.TechnePOC.repository;


import com.example.TechnePOC.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

}
