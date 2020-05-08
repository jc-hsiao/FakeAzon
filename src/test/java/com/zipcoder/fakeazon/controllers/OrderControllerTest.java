package com.zipcoder.fakeazon.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipcoder.fakeazon.models.Order;
import com.zipcoder.fakeazon.services.OrderServices;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @MockBean
    private OrderServices orderService;

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    @DisplayName("GET /order/1")
//    public void getOrderByIdTest() throws Exception{
//        Order mockOrder = new Order();
//        doReturn(mockOrder).when(orderService).save(mockOrder);
//        doReturn(Optional.of(mockOrder)).when(orderService).findOne(mockOrder.getId());
//
//        mockMvc.perform(get("/order/{orderId}",mockOrder.getId()))
//
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//
////                .andExpect(jsonPath("$.id", is(mockOrder.getId())))
////                .andExpect(jsonPath("$.user", is(mockOrder.getUser())));
//    }

    static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
