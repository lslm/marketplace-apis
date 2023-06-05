package com.lslm.ordersapi.controllers;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.google.gson.Gson;
import com.lslm.ordersapi.entities.Order;
import com.lslm.ordersapi.entities.ProductStock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static final WireMockServer wireMockServer = new WireMockServer(options().port(8081));

    @BeforeAll
    static void beforeAll() {
        wireMockServer.start();
    }

    @BeforeEach
    void setUp() {
        wireMockServer.resetAll();
    }

    @AfterAll
    static void afterAll() {
        wireMockServer.stop();
    }

    @Test
    public void shouldNotCreateOrderWhenProductStockDoesNotExist() throws Exception {
        String productId = "53132797-9371-4c18-b90a-ee1b35863ef5";

        wireMockServer.stubFor(
                WireMock.get(urlPathEqualTo("/api/stocks/products/" + productId + "/available"))
                        .willReturn(aResponse()
                                .withStatus(404)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{}")));

        String requestBody = """
        {
           "productId": "53132797-9371-4c18-b90a-ee1b35863ef5",
           "quantitiy": 10
        }        
        """;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/orders")
                .content(requestBody)
                .contentType("application/json")).andExpect(status().isNotFound())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        assertEquals("", responseBody);
    }

    @Test
    public void shouldCreateOrderWhenQuantityIsAvailable() throws Exception {
        String productId = "53132797-9371-4c18-b90a-ee1b35863ef5";

        ProductStock productStock = new ProductStock();
        productStock.setProductId(UUID.fromString(productId));
        productStock.setAvailableQuantity(10);
        Gson gson = new Gson();

        wireMockServer.stubFor(
                WireMock.get(urlPathEqualTo("/api/stocks/products/" + productId + "/available"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(gson.toJson(productStock))));

        String requestBody = """
        {
           "productId": "53132797-9371-4c18-b90a-ee1b35863ef5",
           "quantitiy": 5
        }        
        """;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/orders")
                        .content(requestBody)
                        .contentType("application/json")).andExpect(status().isCreated())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();

        Order responseOrder = gson.fromJson(responseBody, Order.class);

        Order expectedOrder = new Order();
        expectedOrder.setQuantity(5);
        expectedOrder.setProductId(UUID.fromString(productId));

        assertEquals(expectedOrder.getQuantity(), responseOrder.getQuantity());
        assertEquals(expectedOrder.getProductId(), responseOrder.getProductId());
    }
}
