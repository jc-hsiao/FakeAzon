package com.zipcoder.fakeazon.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressTest {

    Address address;

    @Before
    public void setup(){
        address = new Address();
    }

    @Test
    public void getStreet() {
        Assert.assertNull(address.getStreet());
    }

    @Test
    public void setStreet() {
        String expected = "1234 tea ave";
        address.setStreet(expected);
        Assert.assertEquals(expected, address.getStreet());
    }

    @Test
    public void getCity() {
        Assert.assertNull(address.getCity());
    }

    @Test
    public void setCity() {
        String expected = "Teapoton";
        address.setCity(expected);
        Assert.assertEquals(expected, address.getCity());
    }

    @Test
    public void getState() {
        Assert.assertNull(address.getState());
    }

    @Test
    public void setState() {
        String expected = "CP";
        address.setState(expected);
        Assert.assertEquals(expected, address.getState());
    }

    @Test
    public void getPostalCode() {
        Assert.assertEquals(0, address.getPostalCode());
    }

    @Test
    public void setPostalCode() {
        int expected = 111;
        address.setPostalCode(expected);
        Assert.assertEquals(expected, address.getPostalCode());
    }
}