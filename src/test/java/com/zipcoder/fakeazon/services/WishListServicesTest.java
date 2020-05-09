package com.zipcoder.fakeazon.services;

import com.zipcoder.fakeazon.models.WishList;
import com.zipcoder.fakeazon.repositories.ItemRepository;
import com.zipcoder.fakeazon.repositories.WishListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("h2")
public class WishListServicesTest {

    @Autowired
    private WishListServices service;
    @Autowired
    private UserServices userServices;
    @Autowired
    private ItemServices itemServices;
    @MockBean
    private WishListRepository repository;

    @Test
    public void findById() {
    }

    @Test
    public void createWishList() {
    }

    @Test
    public void changeName() {
    }

    @Test
    public void addItem() {
    }

    @Test
    public void removeItem() {
    }

    @Test
    public void delteWishList() {
    }

    @Test
    public void checkIfWishListExists() {
    }
}