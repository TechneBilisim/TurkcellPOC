package com.example.TechnePOC.controller;

import com.example.TechnePOC.TechnePocApplication;
import lombok.RequiredArgsConstructor;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RequiredArgsConstructor
@SpringBootTest(classes = TechnePocApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ProductControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void testGetProduct() {
        ResponseEntity<List> response = testRestTemplate.getForEntity("http://localhost:8080/getProductList", List.class);
        assertNotNull("testGetProduct response null", response);
        MatcherAssert.assertThat("testGetProduct response fail code", response.getStatusCodeValue(), Matchers.equalTo(200));
        List<Map<String, String>> productList = response.getBody();
        assertNotNull("testGetProduct MenuList null", productList);
    }

    @Test
    public void testUpdateShortNumber() throws URISyntaxException {
        List<String> msisdn = new ArrayList<>();
        msisdn.add("905377415183");
        URI uri = new URI("http://localhost:8080/updateProductInfo");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<List<String>> request = new HttpEntity<>(msisdn, headers);
        ResponseEntity<String> result = testRestTemplate.postForEntity(uri, request, String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
    }
}
