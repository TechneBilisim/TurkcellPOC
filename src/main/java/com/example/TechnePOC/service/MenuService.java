package com.example.TechnePOC.service;


import com.example.TechnePOC.model.Menu;
import com.example.TechnePOC.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public void save(Menu menu) {
        menuRepository.save(menu).block();
    }

    public Flux<Menu> getAll() {
        return menuRepository.findAll();
    }

    public Mono<Long> count() {
        return menuRepository.count();
    }


}
