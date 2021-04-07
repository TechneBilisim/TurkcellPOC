package com.example.TechnePOC.util;

import com.example.TechnePOC.constants.GeneralConstants;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class ApplicationUtilsTest {

    @Test
    void generateRandomCodeTest() {
        String code = String.valueOf(ApplicationUtils.generateRandomDigits(GeneralConstants.shortNumberLength));
        assertThat("generateRandomCode test error, parameter and result length not match", code.length(), Matchers.equalTo(GeneralConstants.shortNumberLength));
    }
}
