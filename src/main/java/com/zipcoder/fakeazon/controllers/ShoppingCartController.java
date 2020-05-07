package com.zipcoder.fakeazon.controllers;

import com.zipcoder.fakeazon.models.ShoppingCart;
import com.zipcoder.fakeazon.services.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ShoppingCartController {

    private ShoppingCartServices cartService;

    @Autowired
    public ShoppingCartController(ShoppingCartServices cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart")
    public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ShoppingCart cart){
        return new ResponseEntity<>(cartService.createShoppingCart(cart), HttpStatus.CREATED);
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<?> findCartById(@PathVariable int id){
        return cartService.findOne(id).map( cart ->
                 ResponseEntity.ok().body(cart)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cart/user/{userId}")
    public ResponseEntity<?> findCartByUser(@PathVariable int userId){
        return cartService.findCartByUser(userId).map( cart ->
                ResponseEntity.ok().body(cart)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cart/all")
    public ResponseEntity<List<ShoppingCart>> findAll(){
        return new ResponseEntity<>(cartService.findAll(), HttpStatus.OK);
    }

}
