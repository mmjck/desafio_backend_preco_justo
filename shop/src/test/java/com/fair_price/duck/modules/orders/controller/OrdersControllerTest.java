package com.fair_price.duck.modules.orders.controller;


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
import com.fair_price.shop.adapters.controllers.model.orders.OrdersRequest;
import com.fair_price.shop.adapters.gateway.database.CustomerGateway;
import com.fair_price.shop.adapters.gateway.database.DuckGateway;
import com.fair_price.shop.adapters.gateway.database.entities.CustomerEntity;
import com.fair_price.shop.adapters.gateway.database.entities.DuckEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ShopApplication.class)
@ActiveProfiles("test")
public class OrdersControllerTest {

        protected final String URL = "/orders";

        private MockMvc mvc;

        @Autowired
        private WebApplicationContext context;

        @Autowired
        CustomerGateway customerGateway;

        @Autowired
        DuckGateway duckGateway;

        @Before
        public void setup() {
                mvc = MockMvcBuilders
                                .webAppContextSetup(context)
                                .build();

                initData();
        }

        private void initData() {
                var customer = CustomerEntity
                                .builder()
                                .name("Teste")
                                .build();

                var duck = DuckEntity
                                .builder()
                                .price(Float.valueOf(10))
                                .build();

                customerGateway.create(customer);
                duckGateway.create(duck);

        }

        @Test
        @DisplayName("Should be able to create a new order")
        public void createNewCustomer() throws Exception {
                var order = OrdersRequest
                                .builder()
                                .customerId(1)
                                .duckId(1)
                                .price(Float.valueOf(50))
                                .build();

                mvc.perform(MockMvcRequestBuilders.post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(com.fair_price.duck.utils.TestUtils.object2Json(order)))
                                .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @DisplayName("Should not be able to create a new order with customer not created")
        public void WithError() throws Exception {

                var order = OrdersRequest
                                .builder()
                                .customerId(2) // This customer doesn't exists
                                .duckId(1)
                                .price(Float.valueOf(50))
                                .build();

                mvc.perform(MockMvcRequestBuilders.post(URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(com.fair_price.duck.utils.TestUtils.object2Json(order)))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        }

}
