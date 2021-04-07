package com.example.TechnePOC;


import com.example.TechnePOC.model.Menu;
import com.example.TechnePOC.model.Product;
import com.example.TechnePOC.service.MenuService;
import com.example.TechnePOC.service.ProductService;
import com.example.TechnePOC.util.ApplicationUtils;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
@RequiredArgsConstructor
public class StartupInitializer {

    private static final Logger logger = LogManager.getLogger(StartupInitializer.class);
    private final MenuService menuService;
    private final ProductService productService;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {

        logger.info("Init started");
        Long menuCount = menuService.count().block();
        if (menuCount != null && menuCount == 0) {
            String[] menuList =
                    {"Fatura İşlemleri",
                            "Tarife İşlemleri",
                            "Paket İşlemleri",
                            "Hat İşlemleri",
                            "Ayarlar",
                            "Ürün ve Servisler",
                            "Faturasız Hat İşlemleri",
                            "Yetkili ve Kullanıcı İşlemleri",
                            "Raportlama Merkezi",
                            "İştecep Kampanyası"};

            for (String item :
                    menuList) {
                Menu menu = Menu.builder().name(item).build();
                menuService.save(menu);
            }
            logger.info("Menu init succeed");
        } else {
            logger.info("Menu already init");
        }

        Long productCount = productService.count().block();
        if (productCount != null && productCount == 0) {
            Random rand = new Random();
            String[] userNameList =
                    {"Altuğ Yılmaz",
                            "Ahmet Tunç",
                            "Pelin Elif Özdemir",
                            "Mehmet Emre Dönmez"};

            for (int i = 0; i < 1000; i++) {
                String code = String.valueOf(ApplicationUtils.generateRandomDigits(8));
                Product product = Product.builder().msisdn("9053" + code).lineStatus(true).lineType("Hat Tipi").paymentType("Ödeme Tipi").username(userNameList[rand.nextInt(3)]).shortNumber("1111").build();
                productService.save(product);
            }
            logger.info("Product init succeed");
        } else {
            logger.info("Product already init");
        }
    }

}
