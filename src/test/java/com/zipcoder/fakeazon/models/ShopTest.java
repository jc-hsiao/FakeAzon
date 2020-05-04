package com.zipcoder.fakeazon.models;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ShopTest {
    private Shop shop;

    @Before
    public void setup(){
        this.shop = new Shop();
    }

    @Test
    public void setIdTest(){
        shop.setId(1);
        int expected = 1;
        int actual = shop.getId();
        assertEquals(expected, actual);
    }

    @Test
    public void setNameTest(){
        shop.setName("My Shop");
        String expected = "My Shop";
        String actual = shop.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void setDescriptionTest(){
        shop.setDescription("Description");
        String expected = "Description";
        String actual = shop.getDescription();
        assertEquals(expected, actual);
    }

    @Test
    public void setItemsTest(){
        Item[] items = {new Item(), new Item()};
        List<Item> itemList = Arrays.asList(items);
        shop.setItems(itemList);
        int expected = 2;
        int actual = shop.getItems().size();
        assertEquals(expected, actual);
    }

    @Test
    public void setLogoUrlTest(){
        shop.setLogoUrl("logo.png");
        String expected = "logo.png";
        String actual = shop.getLogoUrl();
        assertEquals(expected, actual);
    }

    @Test
    public void setDateCreatedTest(){
        LocalDate previous = LocalDate.of(2020, 1, 20);
        LocalDate date = LocalDate.now();
        shop.setDateCreated(date);
        LocalDate created = shop.getDateCreated();
        assertTrue(created.isAfter(previous));
    }

    @Test
    public void setKeywordsTest(){
        String[] types = {"sports", "toys"};
        List<String> keywords = Arrays.asList(types);
        shop.setKeywords(keywords);
        int expected = 2;
        int actual = shop.getKeywords().size();
        assertEquals(expected, actual);
    }

    @Test
    public void setOwnerTest(){
        User owner = new User();
        owner.setFirstName("George");
        shop.setOwner(owner);
        String expected = "George";
        String actual = shop.getOwner().getFirstName();
        assertEquals(expected, actual);
    }
}
