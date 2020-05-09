package com.zipcoder.fakeazon.controllers;

import com.zipcoder.fakeazon.models.ItemCount;
import com.zipcoder.fakeazon.models.ShoppingCart;
import com.zipcoder.fakeazon.services.ShoppingCartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShoppingCartController {

    private ShoppingCartServices cartService;

    @Autowired
    public ShoppingCartController(ShoppingCartServices cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/user/{userId}/cart/create")
    public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ShoppingCart cart, @PathVariable Integer userId){
        return new ResponseEntity<>(cartService.createShoppingCart(cart, userId), HttpStatus.CREATED);
    }

//    @PostMapping("/itemCount")
//    public ResponseEntity<ItemCount> createItemCount(@RequestBody ItemCount itemCount){
//        return new ResponseEntity<>(cartService.createItemCount(itemCount), HttpStatus.CREATED);
//    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<?> findCartById(@PathVariable Integer id){
        return cartService.findOne(id).map( cart ->
                 ResponseEntity.ok().body(cart)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cart/user/{userId}")
    public ResponseEntity<?> findCartByUser(@PathVariable Integer userId){
        return cartService.findCartByUser(userId).map( cart ->
                ResponseEntity.ok().body(cart)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cart/all")
    public ResponseEntity<List<ShoppingCart>> findAll(){
        return new ResponseEntity<>(cartService.findAll(), HttpStatus.OK);
    }

//    @PutMapping("/cart/{cartId}/item/{countId}")
//    public ResponseEntity<ShoppingCart> addItemCountToCart(@PathVariable int cartId, @PathVariable int countId){
//        return new ResponseEntity<>(cartService.addItemCountToCart(cartId, countId), HttpStatus.OK);
//    }
}
