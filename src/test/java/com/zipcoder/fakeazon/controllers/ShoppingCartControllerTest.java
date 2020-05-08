package com.zipcoder.fakeazon.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipcoder.fakeazon.models.ShoppingCart;
import com.zipcoder.fakeazon.models.User;
import com.zipcoder.fakeazon.services.ShoppingCartServices;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Optional;
import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingCartServices cartService;


//    @Test
//    @DisplayName("POST /cart")
//    public void createShoppingCartTest() throws Exception{
//        ShoppingCart mockCart = new ShoppingCart();
//        given(cartService.createShoppingCart(mockCart)).willReturn(mockCart);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                            .post("/cart")
//                            .content(asJsonString(new ShoppingCart()))
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//    }

//    @Test
//    @DisplayName("GET /cart/1")
//    public void findCartByIdTest() throws Exception{
//        ShoppingCart mockCart = new ShoppingCart(1, new User(), 15.55);
//        given(cartService.createShoppingCart(mockCart)).willReturn(mockCart);
//        given(cartService.findOne(1)).willReturn(any());
//
//        mockMvc.perform(get("/cart/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(mockCart))
//        )
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.total", is(15.55)));
//    }

    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
