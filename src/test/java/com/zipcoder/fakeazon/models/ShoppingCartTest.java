package com.zipcoder.fakeazon.models;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class ShoppingCartTest {
    private ShoppingCart cart;

    @Before
    public void setup(){
        this.cart = new ShoppingCart();
    }

    @Test
    public void getIdTest(){
        assertNotEquals(cart.getId(), 1);
    }

    @Test
    public void setIdTest(){
        cart.setId(1);
        int expected = 1;
        int actual = cart.getId();
        assertEquals(expected, actual);
    }

    @Test
    public void getOwnerTest(){
        assertNull(cart.getOwner());
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
    public void getTotalTest(){
        assertEquals(cart.getTotal(), 0.00, .001);
    }

    @Test
    public void setTotalTest(){
        cart.setTotal(45.55);
        double expected = 45.55;
        double actual = cart.getTotal();
        assertEquals(expected, actual, .001);
    }

    @Test
    public void getItemCounts() {
        assertNotNull(cart.getItemCounts());
    }

    @Test
    public void setItemCounts() {
        ArrayList<ItemCount> expected = new ArrayList<>();
        cart.setItemCounts(expected);
        assertEquals(expected, cart.getItemCounts());
    }

}
