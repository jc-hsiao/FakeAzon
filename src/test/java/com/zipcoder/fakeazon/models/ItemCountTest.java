package com.zipcoder.fakeazon.models;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemCountTest {

    private ItemCount itemCount;

    @Before
    public void setup(){
        this.itemCount = new ItemCount();
    }

    @Test
    public void getCountIdTest(){
        assertNotEquals(itemCount.getCountId(), 1);
    }

    @Test
    public void setCountIdTest(){
        itemCount.setCountId(1);
        int expected = 1;
        int actual = itemCount.getCountId();
        assertEquals(expected, actual);
    }

    @Test
    public void getItemIdTest(){
        assertNotEquals(itemCount.getItemId(), 1);
    }

    @Test
    public void setItemIdTest(){
        itemCount.setItemId(1);
        int expected = 1;
        int actual = itemCount.getItemId();
        assertEquals(expected, actual);
    }

    @Test
    public void getAmountTest(){
        assertNotEquals(itemCount.getAmount(), 1);
    }

    @Test
    public void setAmountTest(){
        itemCount.setAmount(50);
        int expected = 50;
        int actual = itemCount.getAmount();
        assertEquals(expected, actual);
    }
}
