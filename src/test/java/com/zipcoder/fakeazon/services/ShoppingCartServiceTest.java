package com.zipcoder.fakeazon.services;

import com.zipcoder.fakeazon.models.ItemCount;
import com.zipcoder.fakeazon.models.ShoppingCart;
import com.zipcoder.fakeazon.models.User;
import com.zipcoder.fakeazon.repositories.ItemCountRepository;
import com.zipcoder.fakeazon.repositories.ShoppingCartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.BDDMockito.given;
import static org.junit.Assert.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
public class ShoppingCartServiceTest {

    @Autowired
    private ShoppingCartServices service;

    @MockBean
    private ShoppingCartRepository cartRepo;

    @MockBean
    private ItemCountRepository countRepo;

    private ShoppingCart mockCart = new ShoppingCart();
    private ItemCount itemCount = new ItemCount();

    @Test
    public void createCartTest(){
        given(cartRepo.save(mockCart)).willReturn(mockCart);
        assertNotNull(service.createShoppingCart(mockCart));
    }

    @Test
    public void findByIdTest() throws Exception{
        given(cartRepo.findById(1)).willReturn(Optional.of(mockCart));
        assertTrue(service.findOne(1).isPresent());
    }

    @Test
    public void findAllShoppingCartsTest() throws Exception{
        List<ShoppingCart> carts = new ArrayList<>(Arrays.asList(new ShoppingCart(), new ShoppingCart()));
        given(cartRepo.findAll()).willReturn(carts);
        assertEquals(2, service.findAll().size());
    }

    @Test
    public void findCartByUserTest() throws Exception{
        User user = new User();
        given(cartRepo.findShoppingCartByOwner_Id(user.getId())).willReturn(Optional.of(mockCart));
        assertTrue(service.findCartByUser(user.getId()).isPresent());
    }

    @Test
    public void addItemCountTOCartTest() throws Exception{
        given(countRepo.findById(1)).willReturn(Optional.of(itemCount));
        given(cartRepo.getOne(1)).willReturn(mockCart);
        service.addItemCountToCart(1, itemCount);
        assertTrue(mockCart.getItemCounts().contains(itemCount));
    }

    @Test
    public void removeItemCountFromCart() throws Exception{
        given(countRepo.getOne(1)).willReturn(itemCount);
        given(cartRepo.getOne(1)).willReturn(mockCart);
        service.removeItemCountFromCart(1,1);
        assertFalse(mockCart.getItemCounts().contains(itemCount));
    }

    @Test
    public void updateItemCountQuantityTest() throws Exception{
        mockCart.getItemCounts().add(itemCount);
        given(cartRepo.getOne(1)).willReturn(mockCart);
        given(countRepo.getOne(1)).willReturn(itemCount);
        service.updateItemCountQuantity(1,1, 5);
        int expected = 5;
        int actual = mockCart.getItemCounts().get(0).getAmount();
        assertEquals(expected, actual);
    }

    @Test
    public void clearAllItemsFromEmptyCartTest(){
        User owner = new User();
        given(cartRepo.findShoppingCartByOwner_Id(owner.getId())).willReturn(Optional.of(mockCart));
        assertFalse(service.clearAllItemsFromCart(owner.getId()));
    }

    @Test
    public void clearAllItemsFromCartWithItemsTest(){
        List<ItemCount> itemCounts = new ArrayList<>(Arrays.asList(new ItemCount(), new ItemCount()));
        User owner = new User();
        mockCart.setItemCounts(itemCounts);
        given(countRepo.findAll()).willReturn(itemCounts);
        given(cartRepo.findShoppingCartByOwner_Id(owner.getId())).willReturn(Optional.of(mockCart));
        assertTrue(service.clearAllItemsFromCart(owner.getId()));
    }

}
