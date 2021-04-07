package com.example.TechnePOC;

import com.example.TechnePOC.controller.MenuController;
import com.example.TechnePOC.controller.ProductController;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;

@RequiredArgsConstructor
@SpringBootTest
class SmokeTest {


    @Autowired
    private MenuController menuController;

    @Autowired
    private ProductController productController;


    @Test
    void contextLoads() {
        assertNotNull("MenuController is not loaded", menuController);
        assertNotNull("ProductController is not loaded", productController);
    }
}
