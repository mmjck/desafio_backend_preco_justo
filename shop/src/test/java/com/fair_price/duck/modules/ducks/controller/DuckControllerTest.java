package com.fair_price.duck.modules.ducks.controller;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fair_price.shop.ShopApplication;
import com.fair_price.shop.adapters.controllers.model.duck.DuckRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ShopApplication.class)
@ActiveProfiles("test")
public class DuckControllerTest {

        protected final String URL = "/orders";
        private MockMvc mvc;

        @Autowired
        private WebApplicationContext context;

        @Before
        public void setup() {
                mvc = MockMvcBuilders
                                .webAppContextSetup(context)
                                .build();
        }

        @Test
        @DisplayName("Should be able to create a new duck")
        public void createNewDuck() throws Exception {
                var duck = DuckRequest
                                .builder()
                                .price(Float.valueOf(10))
                                .build();

                mvc.perform(MockMvcRequestBuilders.post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(com.fair_price.duck.utils.TestUtils.object2Json(duck)))

                                .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @DisplayName("Should be able to create a new duck with parentId")
        public void createNewDuckWithParentId() throws Exception {
                var duck = DuckRequest
                                .builder()
                                .price((float) 10)
                                .parentId(1)
                                .build();

                mvc.perform(MockMvcRequestBuilders.post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(com.fair_price.duck.utils.TestUtils.object2Json(duck)))

                                .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @DisplayName("Should not be able to create a new duck")
        public void WithError() throws Exception {

                Object duck = new HashMap<>();
                mvc.perform(MockMvcRequestBuilders.post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(com.fair_price.duck.utils.TestUtils.object2Json(duck)))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        }

}
