package com.zipcoder.fakeazon.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipcoder.fakeazon.models.Order;
import com.zipcoder.fakeazon.models.ShoppingCart;
import com.zipcoder.fakeazon.models.User;
import com.zipcoder.fakeazon.services.OrderServices;
import com.zipcoder.fakeazon.services.UserServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
public class OrderControllerTest {

    @MockBean
    private OrderServices orderService;

    @MockBean
    private UserServices userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /order/create")
    public void createOrderTest() throws Exception{
        Order mockOrder = new Order(1, 0);
        given(orderService.createOrder(mockOrder)).willReturn(mockOrder);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/order/create")
                .content(asJsonString(new Order()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("GET /order/1 -> Success")
    public void findByIdTest() throws Exception{
        Order mockOrder = new Order(1, 0);
        given(orderService.findOne(1)).willReturn(Optional.of(mockOrder));

        mockMvc.perform(get("/order/{orderId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockOrder)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.status", is(0)));
    }

    @Test
    @DisplayName("GET /order/1 -> Not Found")
    public void findOrderByIdFailTest() throws Exception{
        given(orderService.findOne(1)).willReturn(Optional.empty());

        mockMvc.perform(get("/order/{orderId}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /orders/all")
    public void findAllOrdersTest() throws Exception{
        Order mockOrder1 = new Order(1,0);
        Order mockOrder2 = new Order(2, 1);
        List<Order> orders = new ArrayList<>(Arrays.asList(mockOrder1, mockOrder2));
        given(orderService.findAll()).willReturn(orders);

        mockMvc.perform(get("/orders/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].status", is(0)))

                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].status", is(1)));
    }

    @Test
    @DisplayName("GET /orders/user/1")
    public void findOrdersByUserTest() throws Exception{
        Order mockOrder1 = new Order(1,0);
        Order mockOrder2 = new Order(2, 1);
        User mockUser = new User(1, "Moe", "Aydin", "password", "moe@email.com");
        List<Order> orders = new ArrayList<>(Arrays.asList(mockOrder1, mockOrder2));
        given(orderService.findOrdersByUser(1)).willReturn(Optional.of(orders));
        given(userService.findUserById(1)).willReturn(Optional.of(mockUser));

        mockMvc.perform(get("/orders/user/{userId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].status", is(0)))

                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].status", is(1)));
    }

    @Test
    @DisplayName("/order/1/status")
    public void updateOrderStatusTest() throws Exception{
        Order mockOrder = new Order(1,0);
        Order updatedtOrder = new Order(1,1);
        given(orderService.updateStatus(mockOrder.getId())).willReturn(updatedtOrder);

        mockMvc.perform(put("/order/{orderId}/status", 1)
                .header(HttpHeaders.IF_MATCH, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockOrder)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is(1)));
    }

    static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
