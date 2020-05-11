package com.zipcoder.fakeazon.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipcoder.fakeazon.models.ItemCount;
import com.zipcoder.fakeazon.models.ShoppingCart;
import com.zipcoder.fakeazon.models.User;
import com.zipcoder.fakeazon.repositories.ItemCountRepository;
import com.zipcoder.fakeazon.services.ShoppingCartServices;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("local")
public class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingCartServices cartService;

    @MockBean
    private UserServices userService;

    @Test
    @DisplayName("POST /user/{userId}/cart/create")
    public void createShoppingCartTest() throws Exception{
        ShoppingCart mockCart = new ShoppingCart();
        User mockUser = new User();
        given(cartService.createShoppingCart(mockCart, 1)).willReturn(mockCart);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/user/{userId}/cart/create", 1)
                .content(asJsonString(new ShoppingCart()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("GET /cart/1 -> Success")
    public void findCartByIdTest() throws Exception{
        ShoppingCart mockCart = new ShoppingCart(1, new User(), 15.55);
        given(cartService.findOne(1)).willReturn(Optional.of(mockCart));

        mockMvc.perform(get("/cart/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockCart)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.total", is(15.55)));
    }

    @Test
    @DisplayName("GET /cart/1 -> Not Found")
    public void findCartByIdFailTest() throws Exception{
        given(cartService.findOne(1)).willReturn(Optional.empty());

        mockMvc.perform(get("/cart/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /cart/all")
    public void findAllCartsTest() throws Exception{
        ShoppingCart mockCart = new ShoppingCart(1, new User(), 15.55);
        ShoppingCart mockCart1 = new ShoppingCart(2, new User(), 35.95);
        List<ShoppingCart> carts =new ArrayList<>(Arrays.asList(mockCart, mockCart1));
        given(cartService.findAll()).willReturn(carts);

        mockMvc.perform(get("/cart/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].total", is(15.55)))

                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].total", is(35.95)));
    }

    @Test
    @DisplayName("GET /cart/user/{userId}")
    public void findCartByUserTest() throws Exception{
        User mockUser = new User(1, "Moe", "Aydin", "password", "moe@email.com");
        ShoppingCart mockCart = new ShoppingCart(1, mockUser, 15.55);
        given(cartService.findCartByUser(1)).willReturn(Optional.of(mockCart));
        given(userService.findUserById(1)).willReturn(Optional.of(mockUser));

        mockMvc.perform(get("/cart/user/{userId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.total", is(15.55)));
    }

//    @Test
//    @DisplayName("PUT /cart/1/item/1")
//    public void addItemCountToCartTest() throws Exception{
//        User mockUser = new User(1, "Moe", "Aydin", "password", "moe@email.com");
//        ShoppingCart mockCart = new ShoppingCart(1, mockUser, 15.55);
//        ItemCount itemCount = new ItemCount();
//        given(cartService.findCartByUser(1)).willReturn(Optional.of(mockCart));
//        given(countRepo.findById(1)).willReturn(Optional.of(itemCount));
//        given(cartService.addItemCountToCart(1,1,5)).willReturn(mockCart);
//        String amount = "5";
//
//        mockMvc.perform(put("/cart/{cartId}/item/{countId}",1,1)
//                        .header(HttpHeaders.IF_MATCH, 1)
//                        .param("quantity", amount))
//
//                        .andExpect(status().isOk())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//
//                        .andExpect(jsonPath("$.itemCounts", is(String.valueOf(itemCount))));
//    }

    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
