package com.zipcoder.fakeazon.controllers;

import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipcoder.fakeazon.models.Item;
import com.zipcoder.fakeazon.services.ItemServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
class ItemControllerTest {

    @MockBean
    private ItemServices service;

    @Autowired
    private MockMvc mockMvc;

    Item mockItem;

    @BeforeEach
    public void setUp(){
        mockItem = new Item();
        mockItem.setId(1);
        mockItem.setName("Mock Item");
        mockItem.setPrice(10.15);
        mockItem.setInventoryCount(10);
    }

    @Test
    @DisplayName("GET /item/show/id | FOUND")
    void testGetItemByIdFound() throws Exception {
        doReturn(Optional.of(mockItem)).when(service).findOne(1);

        mockMvc.perform(get("/item/show/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.name",is("Mock Item")))
                .andExpect(jsonPath("$.price",is(10.15)))
                .andExpect(jsonPath("$.inventoryCount",is(10)));
    }

    @Test
    @DisplayName("GET /item/show/id | NOT FOUND")
    void testGetItemByIdNotFound() throws Exception {
        doReturn(Optional.empty()).when(service).findOne(1);

        mockMvc.perform(get("/item/show/{id}",1))
                .andExpect(status().isNotFound());
    }

}
