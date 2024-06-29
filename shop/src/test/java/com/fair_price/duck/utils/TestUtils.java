package com.fair_price.duck.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

    public static String object2Json(Object ob) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(ob);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
