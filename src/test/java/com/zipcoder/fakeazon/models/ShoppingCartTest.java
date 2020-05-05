package com.zipcoder.fakeazon.models;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ShoppingCartTest {
    private ShoppingCart cart;

    @Before
    public void setup(){
        this.cart = new ShoppingCart();
    }

    @Test
    public void setIdTest(){
        cart.setId(1);
        int expected = 1;
        int actual = cart.getId();
        assertEquals(expected, actual);
    }

    @Test
    public void setOwnerTest(){
        User owner = new User();
        owner.setFirstName("George");
        cart.setOwner(owner);
        String expected = "George";
        String actual = cart.getOwner().getFirstName();
        assertEquals(expected, actual);
    }

    @Test
    public void setTotalTest(){
        cart.setTotal(45.55);
        double expected = 45.55;
        double actual = cart.getTotal();
        assertEquals(expected, actual, .001);
    }

    @Test
    public void setDateTest(){
        LocalDate previous = LocalDate.of(2020, 1, 20);
        LocalDate date = LocalDate.now();
        cart.setDatePlaced(date);
        LocalDate created = cart.getDatePlaced();
        assertTrue(created.isAfter(previous));
    }

    @Test
    public void setItemsTest(){
        Item[] items = {new Item(), new Item()};
        List<Item> list = Arrays.asList(items);
        cart.setItems(list);
        int expected = 2;
        int actual = cart.getItems().size();
        assertEquals(expected, actual);
    }

    @Test
    public void setStatusTest(){
        cart.setStatus(1);
        int expected = 1;
        int actual = cart.getStatus();
        assertEquals(expected, actual);
    }
}
