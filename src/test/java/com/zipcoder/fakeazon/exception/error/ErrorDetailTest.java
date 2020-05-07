package com.zipcoder.fakeazon.exception.error;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class ErrorDetailTest {

    ErrorDetail error;

    @Before
    public void setUp() throws Exception {
        error = new ErrorDetail();
    }

    @Test
    public void getTitle() {
        assertNull(error.getTitle());
    }

    @Test
    public void setTitle() {
        String expected = "Input Error!";
        error.setTitle(expected);
        String actual = error.getTitle();
        assertEquals(expected,actual);
    }

    @Test
    public void getStatus() {
        assertEquals(0, error.getStatus());
    }

    @Test
    public void setStatus() {
        int expected = 1;
        error.setStatus(expected);
        int actual = error.getStatus();
        assertEquals(expected,actual);
    }

    @Test
    public void getDetail() {
        assertNull(error.getDetail());
    }

    @Test
    public void setDetail() {
        String expected = "The item the client was looking for does not exist!";
        error.setDetail(expected);
        String actual = error.getDetail();
        assertEquals(expected,actual);
    }

    @Test
    public void getTimeStamp() {
        assertEquals(0L, error.getTimeStamp());
    }

    @Test
    public void setTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        long expected = new Timestamp(System.currentTimeMillis()).getTime();
        error.setTimeStamp(expected);
        long actual = error.getTimeStamp();
        System.out.println(sdf.format(actual));
        assertEquals(expected,actual);
    }

    @Test
    public void getDeveloperMessage() {
        assertNull(error.getDeveloperMessage());
    }

    @Test
    public void setDeveloperMessage() {
        String expected = "This error was thrown by the NotFoundException! ";
        error.setDeveloperMessage(expected);
        String actual = error.getDeveloperMessage();
        assertEquals(expected,actual);
    }
}
