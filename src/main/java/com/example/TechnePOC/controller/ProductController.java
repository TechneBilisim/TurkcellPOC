package com.example.TechnePOC.controller;


import com.example.TechnePOC.constants.GeneralConstants;
import com.example.TechnePOC.model.Log;
import com.example.TechnePOC.model.Product;
import com.example.TechnePOC.service.LogService;
import com.example.TechnePOC.service.ProductService;
import com.example.TechnePOC.util.ApplicationUtils;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping
public class ProductController {

    private static final Logger logger = LogManager.getLogger(ProductController.class);
    private final ProductService productService;
    private final LogService logService;

    @GetMapping("/getProductList")
    public ResponseEntity<Flux<Product>> getProductList() {
        Flux<Product> result;
        try {
            logService.save(Log.builder().date(new Date()).serviceName(GeneralConstants.getProductListMethodName).build());
            result = productService.getAll();
            logger.info("getProductList success");
        } catch (Exception e) {
            logger.error("getProductList error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(result);
    }


    @PostMapping(path = "/updateProductInfo")
    public ResponseEntity<ParallelFlux<Object>> updateProductInfo(@RequestBody List<String> msisdnList) {
        ParallelFlux<Object> result;
        try {
            logService.save(Log.builder().date(new Date()).serviceName(GeneralConstants.updateProductInfoMethodName).params(msisdnList.stream().map(String::valueOf)
                    .collect(Collectors.joining(",", "[", "]"))).build());
            result = Flux.range(0, msisdnList.size())
                    .parallel(2)
                    .runOn(Schedulers.parallel())
                    .map(n -> {  productService.findByMsisdn(msisdnList.get(n)).subscribe(
                                        value -> productService.save(Product.builder()
                                                .msisdn(value.getMsisdn())
                                                .lineType(value.getLineType())
                                                .lineStatus(value.isLineStatus())
                                                .paymentType(value.getPaymentType())
                                                .username(value.getUsername())
                                                .id(value.getId())
                                                .shortNumber(String.valueOf(ApplicationUtils.generateRandomDigits(GeneralConstants.shortNumberLength)))
                                                .build())
                                );
                        return "index = "+ n + " Processed";
                    });

            logger.info("updateProductInfo success. Updated list size = " + msisdnList.size());
        } catch (Exception e) {
            logger.error("updateProductInfo error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(result);
    }

}
