package com.example.TechnePOC.controller;


import com.example.TechnePOC.TechnePocApplication;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertNotNull;


@SpringBootTest(classes = TechnePocApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class MenuControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void testGetMenu() {
        ResponseEntity<List> response = testRestTemplate.getForEntity("http://localhost:8080/getMenuList", List.class);
        assertNotNull("testGetMenu response null", response);
        MatcherAssert.assertThat("testGetMenu response fail code", response.getStatusCodeValue(), Matchers.equalTo(200));
        List<Map<String, String>> menuList = response.getBody();
        assertNotNull("testGetMenu MenuList null", menuList);
    }
}
