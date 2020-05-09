package com.zipcoder.fakeazon.controllers;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipcoder.fakeazon.models.Item;
import com.zipcoder.fakeazon.models.Shop;
import com.zipcoder.fakeazon.services.ItemServices;
import com.zipcoder.fakeazon.services.ShopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
class ShopControllerTest {

    @MockBean
    private ShopService service;

    @Autowired
    private MockMvc mockMvc;

    Shop mockShop;
    Shop putShop;

    @BeforeEach
    public void setUp() {
        mockShop = new Shop();
        mockShop.setId(1);
        mockShop.setName("Mock Shop");
        mockShop.setLogoUrl("imgur.com/test");

        putShop = new Shop();
        putShop.setId(1);
        putShop.setName("Put Shop");
        putShop.setLogoUrl("imgur.com/put");
        putShop.setDescription("TEST TEST TEST");
    }

    @Test
    @DisplayName("GET /shop/show/id | FOUND")
    void testGetShopByIdFound() throws Exception {
        doReturn(Optional.of(mockShop)).when(service).findOne(1);

        mockMvc.perform(get("/shop/show/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.name",is("Mock Shop")))
                .andExpect(jsonPath("$.logoUrl",is("imgur.com/test")));
    }

    @Test
    @DisplayName("GET /shop/show/id | NOT FOUND")
    void testGetItemByIdNotFound() throws Exception {
        doReturn(Optional.empty()).when(service).findOne(1);

        mockMvc.perform(get("/shop/show/{id}",1))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /shop/show")
    void testGetAllShops() throws Exception {
        List<Shop> shops = new ArrayList<>(Arrays.asList(new Shop(), new Shop()));
        shops.get(0).setId(1);
        shops.get(1).setId(2);
        doReturn(shops).when(service).findAll();

        mockMvc.perform(get("/shop/show"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    @DisplayName("POST /shop/create")
    void testCreateShop() throws Exception {
        doReturn(mockShop).when(service).saveShop(mockShop);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/shop/create")
                .content(asJsonString(mockShop))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("DELETE /shop/delete/{id}")
    void testDeleteShop() throws Exception {
        doReturn(true).when(service).deleteShop(1);

        MvcResult result = mockMvc.perform(delete("/shop/delete/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(true)))

                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals("true",content);
    }

    @Test
    @DisplayName("PUT /shop/update/name/{id}")
    void testUpdateName() throws Exception {
        doReturn(Optional.of(mockShop)).when(service).findOne(1);
        doReturn(putShop).when(service).updateName(1, "Put Shop");

        mockMvc.perform(put("/shop/update/name/{id}",1)
                .param("name", "Put Shop")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putShop)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Put Shop")));
    }

    @Test
    @DisplayName("PUT /shop/update/description/{id}")
    void testUpdateDescription() throws Exception {
        doReturn(Optional.of(mockShop)).when(service).findOne(1);
        doReturn(putShop).when(service).updateDescription(1, "TEST TEST TEST");

        mockMvc.perform(put("/shop/update/description/{id}",1)
                .param("description", "TEST TEST TEST")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putShop)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is("TEST TEST TEST")));
    }

    @Test
    @DisplayName("PUT /shop/update/logoUrl/{id}")
    void testUpdateImage() throws Exception {
        doReturn(Optional.of(mockShop)).when(service).findOne(1);
        doReturn(putShop).when(service).updateLogoUrl(1, "imgur.com/put");

        mockMvc.perform(put("/shop/update/logoUrl/{id}",1)
                .param("logoUrl", "imgur.com/put")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putShop)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.logoUrl", is("imgur.com/put")));
    }

    @Test
    @DisplayName("PUT /shop/add/keywords/{id}")
    void testAddKeywords() throws Exception {
        doReturn(Optional.of(mockShop)).when(service).findOne(1);
        String[] keywords = new String[]{"KW1","KW2"};
        putShop.setKeywords(Arrays.asList(keywords));
        doReturn(putShop).when(service).addKeywords(1, keywords);

        mockMvc.perform(put("/shop/add/keywords/{id}",1)
                .param("keywords", keywords)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putShop)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.keywords[0]", is("KW1")))
                .andExpect(jsonPath("$.keywords[1]", is("KW2")));
    }

    @Test
    @DisplayName("PUT /shop/remove/itemTags/{id}")
    void testRemoveKeywords() throws Exception {
        doReturn(Optional.of(mockShop)).when(service).findOne(1);
        String[] kws = new String[]{"KW2"};
        putShop.setKeywords(Arrays.asList(kws));
        String[] keywords = new String[]{"KW1"};
        doReturn(putShop).when(service).removeKeywords(1, keywords);

        mockMvc.perform(put("/shop/remove/keywords/{id}",1)
                .param("keywords", keywords)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putShop)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.keywords[0]", is("KW2")));
    }

    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
