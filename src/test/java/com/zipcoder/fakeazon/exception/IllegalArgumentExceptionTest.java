package com.zipcoder.fakeazon.exception;

import org.junit.Test;

public class IllegalArgumentExceptionTest {

    @Test(expected = IllegalArgumentException.class)
    public void nullConstructorTest(){
        throw new IllegalArgumentException();
    }

    @Test(expected = IllegalArgumentException.class)
    public void singleParamTest(){
        throw new IllegalArgumentException("Illegal Argument");
    }

    @Test(expected = IllegalArgumentException.class)
    public void doubleParamTest(){
        throw new IllegalArgumentException("Illegal Argument", new Throwable());
    }

}
