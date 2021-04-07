package com.example.TechnePOC.service;


import com.example.TechnePOC.model.Product;
import com.example.TechnePOC.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    private final ReactiveMongoTemplate mongoTemplate;

    public Product save(Product product) {
        productRepository.save(product).block();
        return product;
    }

    public Flux<Product> getAll() {
        return productRepository.findAll();
    }

    public Mono<Long> count() {
        return productRepository.count();
    }

    public Mono<Product> findByMsisdn(String msisdn) {
        Query query = new Query(Criteria.where("msisdn").is(msisdn));
        return mongoTemplate.findOne(query, Product.class);
    }

}
