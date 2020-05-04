package com.zipcoder.fakeazon.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {
    private Order order;

    @Before
    public void setup(){
        this.order = new Order();
    }

    @Test
    public void setIdTest(){
        order.setId(1);
        int expected = 1;
        int actual = order.getId();
        assertEquals(expected, actual);
    }

    @Test
    public void setUserTest(){
        User user = new User();
        user.setFirstName("Leila");
        order.setUser(user);
        String expected = "Leila";
        String actual = order.getUser().getFirstName();
        assertEquals(expected, actual);
    }

    @Test
    public void setCartTest(){
        ShoppingCart cart = new ShoppingCart();
        cart.setTotal(55.55);
        double expected = 55.55;
        order.setCart(cart);
        double actual = order.getCart().getTotal();
        assertEquals(expected, actual, .001);
    }
}
