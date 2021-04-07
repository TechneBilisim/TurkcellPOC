package com.example.TechnePOC.util;

import java.util.Random;

public class ApplicationUtils {


    public static int generateRandomDigits(int digitLength) {
        int m = (int) Math.pow(10, digitLength - 1);
        return m + new Random().nextInt(9 * m);
    }


}
