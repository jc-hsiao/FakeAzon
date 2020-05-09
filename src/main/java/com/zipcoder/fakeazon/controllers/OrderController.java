package com.zipcoder.fakeazon.controllers;

import com.zipcoder.fakeazon.models.Order;
import com.zipcoder.fakeazon.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private OrderServices orderService;

    @Autowired
    public OrderController(OrderServices orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> findById(@PathVariable Integer orderId){
        return orderService.findOne(orderId).map(order ->
                ResponseEntity.ok().body(order)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/orders/all")
    public ResponseEntity<List<Order>> findAll(){
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/orders/user/{userId}")
    public ResponseEntity<?> findOrdersByUser(@PathVariable Integer userId){
        return orderService.findOrdersByUser(userId).map(order ->
                ResponseEntity.ok().body(order)).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/order/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Integer orderId){
        return new ResponseEntity<>(orderService.updateStatus(orderId), HttpStatus.OK);
    }
}
