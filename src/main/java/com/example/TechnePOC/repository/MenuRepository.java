package com.example.TechnePOC.repository;

import com.example.TechnePOC.model.Menu;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MenuRepository extends ReactiveMongoRepository<Menu, String> {

}
