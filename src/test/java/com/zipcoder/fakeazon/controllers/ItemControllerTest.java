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
class ItemControllerTest {

    @MockBean
    private ItemServices service;

    @Autowired
    private MockMvc mockMvc;

    Item mockItem;
    Item putItem;

    @BeforeEach
    public void setUp(){
        mockItem = new Item();
        mockItem.setId(1);
        mockItem.setName("Mock Item");
        mockItem.setPrice(10.15);
        mockItem.setInventoryCount(10);

        putItem = new Item();
        putItem.setId(1);
        putItem.setName("Put Item");
        putItem.setPrice(12.50);
        putItem.setInventoryCount(15);
        putItem.setImageUrl("imgur.com/test");
        putItem.setDescription("TEST TEST TEST");
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

    @Test
    @DisplayName("GET /item/show/shop/{id}")
    void testGetByShopId() throws Exception {
        List<Item> items = new ArrayList<>(Arrays.asList(new Item(), new Item()));
        items.get(0).setId(1);
        items.get(1).setId(2);
        doReturn(items).when(service).findAllByShop(1);

        mockMvc.perform(get("/item/show/shop/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    @DisplayName("POST /item/create")
    void testCreateItem() throws Exception {
        doReturn(mockItem).when(service).saveItem(mockItem);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/item/create")
                .content(asJsonString(mockItem))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("PUT /item/inventory/increase/{id}")
    void testIncreaseInventory() throws Exception {
        doReturn(Optional.of(mockItem)).when(service).findOne(1);
        doReturn(putItem).when(service).increaseInventoryCount(1,5);

        mockMvc.perform(put("/item/inventory/increase/{id}",1)
                .param("value", String.valueOf(5))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putItem)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.inventoryCount", is(15)));
    }

    @Test
    @DisplayName("PUT /item/inventory/decrease/{id}")
    void testDecreaseInventory() throws Exception {
        doReturn(Optional.of(mockItem)).when(service).findOne(1);
        putItem.setInventoryCount(5);
        doReturn(putItem).when(service).decreaseInventoryCount(1,5);

        mockMvc.perform(put("/item/inventory/decrease/{id}",1)
                .param("value", String.valueOf(5))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putItem)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.inventoryCount", is(5)));
    }

    @Test
    @DisplayName("PUT /item/update/name/{id}")
    void testUpdateName() throws Exception {
        doReturn(Optional.of(mockItem)).when(service).findOne(1);
        doReturn(putItem).when(service).updateName(1, "Put Item");

        mockMvc.perform(put("/item/update/name/{id}",1)
                .param("name", "Put Item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putItem)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Put Item")));
    }

    @Test
    @DisplayName("PUT /item/update/imageUrl/{id}")
    void testUpdateImage() throws Exception {
        doReturn(Optional.of(mockItem)).when(service).findOne(1);
        doReturn(putItem).when(service).updateImageUrl(1, "imgur.com/test");

        mockMvc.perform(put("/item/update/imageUrl/{id}",1)
                .param("imageUrl", "imgur.com/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putItem)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.imageUrl", is("imgur.com/test")));
    }

    @Test
    @DisplayName("PUT /item/update/description/{id}")
    void testUpdateDescription() throws Exception {
        doReturn(Optional.of(mockItem)).when(service).findOne(1);
        doReturn(putItem).when(service).updateDescription(1, "TEST TEST TEST");

        mockMvc.perform(put("/item/update/description/{id}",1)
                .param("description", "TEST TEST TEST")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putItem)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is("TEST TEST TEST")));
    }

    @Test
    @DisplayName("PUT /item/update/price/{id}")
    void testUpdatePrice() throws Exception {
        doReturn(Optional.of(mockItem)).when(service).findOne(1);
        doReturn(putItem).when(service).updatePrice(1, 12.50);

        mockMvc.perform(put("/item/update/price/{id}",1)
                .param("price", String.valueOf(12.50))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putItem)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(12.50)));
    }

    @Test
    @DisplayName("PUT /item/add/itemTags/{id}")
    void testAddItemTags() throws Exception {
        doReturn(Optional.of(mockItem)).when(service).findOne(1);
        String[] tags = new String[]{"Tag1","Tag2"};
        putItem.setItemTags(Arrays.asList(tags));
        doReturn(putItem).when(service).addItemTags(1, tags);

        mockMvc.perform(put("/item/add/itemTags/{id}",1)
                .param("tags", tags)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putItem)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemTags[0]", is("Tag1")))
                .andExpect(jsonPath("$.itemTags[1]", is("Tag2")));
    }

    @Test
    @DisplayName("PUT /item/remove/itemTags/{id}")
    void testRemoveItemTags() throws Exception {
        doReturn(Optional.of(mockItem)).when(service).findOne(1);
        String[] tags = new String[]{"Tag2"};
        putItem.setItemTags(Arrays.asList(tags));
        String[] removeTags = new String[]{"Tag1"};
        doReturn(putItem).when(service).removeItemTags(1, removeTags);

        mockMvc.perform(put("/item/remove/itemTags/{id}",1)
                .param("tags", removeTags)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putItem)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemTags[0]", is("Tag2")));
    }

    @Test
    @DisplayName("PUT /item/add/rating/{id}")
    void testAddRating() throws Exception {
        doReturn(Optional.of(mockItem)).when(service).findOne(1);
        List<Double> ratings = new ArrayList<>(Arrays.asList(4.5,5.0,4.8));
        putItem.setRating(ratings);
        doReturn(putItem).when(service).addRating(1, 4.8);

        mockMvc.perform(put("/item/add/rating/{id}",1)
                .param("rating", String.valueOf(4.8))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(putItem)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rating[2]", is(4.8)));
    }

    @Test
    @DisplayName("GET /item/rating/{id}")
    void testGetRating() throws Exception {
        doReturn(Optional.of(putItem)).when(service).findOne(1);
        List<Double> ratings = new ArrayList<>(Arrays.asList(4.5,5.0,4.8));
        putItem.setRating(ratings);
        doReturn(4.77).when(service).getRating(1);

        MvcResult result = mockMvc.perform(get("/item/rating/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(4.77)))

                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals("4.77", content);
    }


    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
