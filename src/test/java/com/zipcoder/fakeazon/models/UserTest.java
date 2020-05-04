package com.zipcoder.fakeazon.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserTest {

    User user;

    @Before
    public void setup(){
        user = new User();
    }

    @Test
    public void getId() {
        Assert.assertEquals(0,user.getId());
    }

    @Test
    public void setId() {
        user.setId(1);
        Assert.assertEquals(1,user.getId());
    }

    @Test
    public void getFirstName() {
        Assert.assertNull(user.getFirstName());
    }

    @Test
    public void setFirstName() {
        String expected = "John";
        user.setFirstName(expected);
        Assert.assertEquals(expected,user.getFirstName());
    }

    @Test
    public void getLastName() {
        Assert.assertNull(user.getLastName());
    }

    @Test
    public void setLastName() {
        String expected = "Doe";
        user.setLastName(expected);
        Assert.assertEquals(expected,user.getLastName());
    }

    @Test
    public void getPassword() {
        Assert.assertNull(user.getPassword());
    }

    @Test
    public void setPassword() {
        String expected = "12345";
        user.setPassword(expected);
        Assert.assertEquals(expected,user.getPassword());
    }

    @Test
    public void getEmail() {
        Assert.assertNull(user.getEmail());
    }

    @Test
    public void setEmail() {
        String expected = "jdoe@gmail.com";
        user.setEmail(expected);
        Assert.assertEquals(expected,user.getEmail());
    }

    @Test
    public void getShoppingCart() {
        Assert.assertNull(user.getShoppingCart());
    }

    @Test
    public void setShoppingCart() {
        ShoppingCart expected = new ShoppingCart();
        user.setShoppingCart(expected);
        Assert.assertEquals(expected,user.getShoppingCart());
    }

    @Test
    public void getOrders() {
        Assert.assertNull(user.getOrders());
    }

    @Test
    public void setOrders() {
        ArrayList<Order> expected = new ArrayList<>();
        user.setOrders(expected);
        Assert.assertEquals(expected,user.getOrders());
    }

    @Test
    public void getWishLists() {
        Assert.assertNull(user.getWishLists());
    }

    @Test
    public void setWishLists() {
        ArrayList<WishList> expected = new ArrayList<>();
        user.setWishLists(expected);
        Assert.assertEquals(expected,user.getWishLists());
    }

    @Test
    public void getShop() {
        Assert.assertNull(user.getShop());
    }

    @Test
    public void setShop() {
        Shop expected = new Shop();
        user.setShop(expected);
        Assert.assertEquals(expected,user.getShop());
    }

    @Test
    public void getAddress() {
        Assert.assertNull(user.getAddress());
    }

    @Test
    public void setAddress() {
        Address expected = new Address();
        user.setAddress(expected);
        Assert.assertEquals(expected,user.getAddress());
    }
}