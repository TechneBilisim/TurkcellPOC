package com.example.TechnePOC.controller;

import com.example.TechnePOC.constants.GeneralConstants;
import com.example.TechnePOC.model.Log;
import com.example.TechnePOC.model.Menu;
import com.example.TechnePOC.service.LogService;
import com.example.TechnePOC.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Date;


@RestController
@RequiredArgsConstructor
@RequestMapping
public class MenuController {

    private static final Logger logger = LogManager.getLogger(MenuController.class);
    private final MenuService menuService;
    private final LogService logService;

    @GetMapping("/getMenuList")
    public ResponseEntity<Flux<Menu>> getMenuList() {
        Flux<Menu> result;
        try {
            logService.save(Log.builder().date(new Date()).serviceName(GeneralConstants.getMenuListMethodName).build());
            result = menuService.getAll();
            logger.info("getMenuList success");
        } catch (Exception e) {
            logger.error("getMenuList error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(result);
    }
}
