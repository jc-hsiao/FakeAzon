package com.zipcoder.fakeazon.models;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
    public void setDateTest(){
        LocalDate previous = LocalDate.of(2020, 1, 20);
        LocalDate date = LocalDate.now();
        order.setDate(date);
        LocalDate created = order.getDate();
        assertTrue(created.isAfter(previous));
    }

    @Test
    public void setTotalPriceTest(){
        order.setTotalPrice(56.55);
        double expected = 56.55;
        double actual = order.getTotalPrice();
        assertEquals(expected, actual, 0.001);
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
    public void setItemCounts(){
        ItemCount[] items = {new ItemCount(), new ItemCount()};
        List<ItemCount> list = Arrays.asList(items);
        order.setItemCounts(list);
        int expected = 2;
        int actual = order.getItemCounts().size();
        assertEquals(expected, actual);
    }
}
