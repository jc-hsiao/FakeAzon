package com.zipcoder.fakeazon.exception;

import org.junit.Test;

public class NotFoundExceptionTest {

    @Test(expected = NotFoundException.class)
    public void nullConstructorTest(){
        throw new NotFoundException();
    }

    @Test(expected = NotFoundException.class)
    public void singleParamConstructor(){
        throw new NotFoundException("Title String");
    }

    @Test(expected = NotFoundException.class)
    public void doubleParamConstructor() {
        throw new NotFoundException("Title", new Throwable());
    }

}
