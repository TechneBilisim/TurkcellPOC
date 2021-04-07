package com.example.TechnePOC.service;


import com.example.TechnePOC.model.Log;
import com.example.TechnePOC.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    @Async
    public void save(Log log) {
        logRepository.save(log).subscribe();
    }


}
