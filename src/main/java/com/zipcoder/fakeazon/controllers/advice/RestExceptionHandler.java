package com.zipcoder.fakeazon.controllers.advice;

import com.zipcoder.fakeazon.exception.NotFoundException;
import com.zipcoder.fakeazon.exception.error.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException nf, HttpServletRequest request){
        ErrorDetail error = new ErrorDetail();
        error.setTitle("Whoops! ¯\\_(ツ)_/¯ Nothing Here!");
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setDetail("The resource you are looking for was not found!");
        error.setTimeStamp(System.currentTimeMillis());
        error.setDeveloperMessage("This error came from " + nf.getCause() + "\n" + nf.getMessage());
        return new ResponseEntity<>(error.getDetail(), HttpStatus.NOT_FOUND);
    }

}
