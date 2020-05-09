package com.zipcoder.fakeazon.controllers;

import com.zipcoder.fakeazon.models.Item;
import com.zipcoder.fakeazon.models.Shop;
import com.zipcoder.fakeazon.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/shop")
@CrossOrigin
public class ShopController {

    private final ShopService service;

    @Autowired
    public ShopController (ShopService service) { this.service = service;}

    @GetMapping("/show/{id}")
    public ResponseEntity<?> findShopById(@PathVariable Integer id){
        return this.service.findOne(id)
                .map(item -> ResponseEntity
                        .ok()
                        .body(item))
                .orElse(ResponseEntity
                        .notFound()
                        .build());
    }

    @GetMapping("/show")
    public ResponseEntity<List<Shop>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Shop> create(@RequestBody Shop shop){
        return new ResponseEntity<>(service.saveShop(shop), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return new ResponseEntity<>(service.deleteShop(id),HttpStatus.OK);
    }

    @PutMapping("/update/name/{id}")
    public ResponseEntity<Shop> updateName(@PathVariable Integer id, @RequestParam String name){
        return new ResponseEntity<>(service.updateName(id,name),HttpStatus.OK);
    }

    @PutMapping("/update/description/{id}")
    public ResponseEntity<Shop> updateDescription(@PathVariable Integer id, @RequestParam String description){
        return new ResponseEntity<>(service.updateDescription(id,description),HttpStatus.OK);
    }

    @PutMapping("/update/logoUrl/{id}")
    public ResponseEntity<Shop> updateLogoUrl(@PathVariable Integer id, @RequestParam String logoUrl){
        return new ResponseEntity<>(service.updateLogoUrl(id,logoUrl),HttpStatus.OK);
    }

    @PutMapping("/add/keywords/{id}")
    public ResponseEntity<Shop> addKeyWords(@PathVariable Integer id, @RequestParam String[] keywords){
        return new ResponseEntity<>(service.addKeywords(id,keywords),HttpStatus.OK);
    }

    @PutMapping("/remove/keywords/{id}")
    public ResponseEntity<Shop> removeKeyWords(@PathVariable Integer id, @RequestParam String[] keywords){
        return new ResponseEntity<>(service.removeKeywords(id,keywords),HttpStatus.OK);
    }

    @PutMapping("/add/items/{id}")
    public ResponseEntity<Shop> addItemsToShop(@PathVariable Integer id, @RequestParam Item[] items){
        return new ResponseEntity<>(service.addItemsToShop(id,items),HttpStatus.OK);
    }

    @PutMapping("/remove/items/{id}")
    public ResponseEntity<Shop> removeItemsToShop(@PathVariable Integer id, @RequestParam Item[] items){
        return new ResponseEntity<>(service.removeItemsFromShop(id,items),HttpStatus.OK);
    }


}
