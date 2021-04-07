package com.example.TechnePOC.repository;

import com.example.TechnePOC.model.Log;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LogRepository extends ReactiveMongoRepository<Log, String> {

}
