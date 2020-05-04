package com.zipcoder.fakeazon.models;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class ItemTest {

    Item item;

    @Before
    public void setUp(){
        item = new Item(1);
    }

    @Test
    public void getId(){
        int expected = 1;
        int actual = item.getId();
        assertEquals(expected,actual);
    }

    @Test
    public void setId(){
        int expected = 3;
        item.setId(3);
        int actual = item.getId();
        assertEquals(expected,actual);
    }

    @Test
    public void getName(){
        assertNull(item.getName());
    }

    @Test
    public void setName(){
        String expected = "Leila's Cup";
        item.setName(expected);
        String actual = item.getName();
        assertEquals(expected,actual);
    }

    @Test
    public void getPrice(){
        double expected = 0.0;
        double actual = item.getPrice();
        assertEquals(expected,actual,2);
    }

    @Test
    public void setPrice(){
        double expected = 12.00;
        item.setPrice(expected);
        double actual = item.getPrice();
        assertEquals(expected,actual,2);
    }

    @Test
    public void getInventoryCount(){
        int expected = 0;
        int actual = item.getInventoryCount();
        assertEquals(expected,actual);
    }

    @Test
    public void setInventoryCount(){
        int expected = 100;
        item.setInventoryCount(expected);
        int actual = item.getInventoryCount();
        assertEquals(expected,actual);
    }

    @Test
    public void getImageUrl(){
        assertNull(item.getImageUrl());
    }

    @Test
    public void setImageUrl(){
        String expected = "https://i.imgur.com/AKF2q5M.jpg";
        item.setImageUrl(expected);
        String actual = item.getImageUrl();
        assertEquals(expected,actual);
    }

    @Test
    public void getDescription(){
        assertNull(item.getImageUrl());
    }

    @Test
    public void setDescription(){
        String expected = "Do you like cups? So do we! Is your name Leila? Well this cup was made for you! Buy it!";
        item.setDescription(expected);
        String actual = item.getDescription();
        assertEquals(expected,actual);
    }


    @Test
    public void getRating(){
        double expected = 0.0;
        double actual = item.getRating();
        assertEquals(expected,actual,2);
    }

    @Test
    public void setRating(){
        double expected = 5.00;
        item.setRating(expected);
        double actual = item.getRating();
        assertEquals(expected,actual,2);
    }

    @Test
    public void getItemTags(){
        Item nullConstructor = new Item();
        assertEquals(0, nullConstructor.getItemTags().size());
    }

    @Test
    public void setItemTags(){
        List<String> expected = new ArrayList<>(Arrays.asList("Kitchen Ware","Cups","Drinking"));
        item.setItemTags(expected);
        List<String> actual = item.getItemTags();
        assertEquals(expected,actual);
    }

    @Test
    public void getShop(){
        assertNull(item.getShop());
    }

    @Test
    public void setShop(){
        Shop expected = new Shop();
        item.setShop(expected);
        Shop actual = item.getShop();
        assertEquals(expected,actual);
    }

}
