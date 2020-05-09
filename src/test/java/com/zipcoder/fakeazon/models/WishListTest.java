package com.zipcoder.fakeazon.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class WishListTest {

    WishList wishList;

    @Before
    public void setup(){
        wishList = new WishList();
    }

    @Test
    public void setId() {
        int expected = 1;
        wishList.setId(expected);
        int actual = wishList.getId();
        Assert.assertEquals(expected, actual );
    }

    @Test
    public void getName() {
        Assert.assertNull(wishList.getName());
    }

    @Test
    public void setName() {
        String expected = "my fav cups!!!!!!!";
        wishList.setName(expected);
        Assert.assertEquals(expected, wishList.getName());
    }

    @Test
    public void getUser() {
        Assert.assertNull(wishList.getUser());
    }

    @Test
    public void setUser() {
        User expected = new User();
        wishList.setUser(expected);
        Assert.assertEquals(expected, wishList.getUser());
    }

    @Test
    public void getItems() {
        Assert.assertNull(wishList.getItems());
    }

    @Test
    public void setItems() {
        ArrayList<Item> expected = new ArrayList<>();
        wishList.setItems(expected);
        Assert.assertEquals(expected, wishList.getItems());
    }
}
