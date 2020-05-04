package com.zipcoder.fakeazon.models;

import org.junit.Before;
import org.junit.Test;

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
    public void setItemCountsTest(){
        ItemCount[] items = {new ItemCount(), new ItemCount()};
        List<ItemCount> list = Arrays.asList(items);
        cart.setItemCounts(list);
        int expected = 2;
        int actual = cart.getItemCounts().size();
        assertEquals(expected, actual);
    }
}
