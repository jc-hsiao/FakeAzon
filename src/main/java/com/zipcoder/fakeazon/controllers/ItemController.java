package com.zipcoder.fakeazon.controllers;


import com.zipcoder.fakeazon.models.Item;
import com.zipcoder.fakeazon.services.ItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/item")
@CrossOrigin
public class ItemController {

    private final ItemServices service;

    @Autowired
    public ItemController (ItemServices service) {this.service = service;}

    @GetMapping("/show/{id}")
    public ResponseEntity<?> findItemById(@PathVariable Integer id){
        return this.service.findOne(id)
                .map(item -> ResponseEntity
                            .ok()
                            .body(item))
                .orElse(ResponseEntity
                        .notFound()
                        .build());
    }

    @GetMapping("/show/shop/{id}")
    public ResponseEntity<List<Item>> findAllByShopId(@PathVariable Integer id) {
        return new ResponseEntity<>(service.findAllByShop(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Item> create(@RequestBody Item item){
        return new ResponseEntity<>(service.saveItem(item), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return new ResponseEntity<>(service.deleteItem(id),HttpStatus.OK);
    }

    @PutMapping("/inventory/increase/{id}")
    public ResponseEntity<Item> increaseInventory(@PathVariable Integer id, @RequestParam Integer value){
        return new ResponseEntity<>(service.increaseInventoryCount(id,value),HttpStatus.OK);
    }

    @PutMapping("/inventory/decrease/{id}")
    public ResponseEntity<Item> decreaseInventory(@PathVariable Integer id, @RequestParam Integer value){
        return new ResponseEntity<>(service.decreaseInventoryCount(id,value),HttpStatus.OK);
    }

    @PutMapping("/update/name/{id}")
    public ResponseEntity<Item> updateName(@PathVariable Integer id, @RequestParam String name){
        return new ResponseEntity<>(service.updateName(id,name),HttpStatus.OK);
    }

    @PutMapping("/update/price/{id}")
    public ResponseEntity<Item> updatePrice(@PathVariable Integer id, @RequestParam Double price){
        return new ResponseEntity<>(service.updatePrice(id,price),HttpStatus.OK);
    }

    @PutMapping("/update/imageUrl/{id}")
    public ResponseEntity<Item> updateImageUrl(@PathVariable Integer id, @RequestParam String imageUrl){
        return new ResponseEntity<>(service.updateImageUrl(id,imageUrl),HttpStatus.OK);
    }

    @PutMapping("/update/description/{id}")
    public ResponseEntity<Item> updateDescription(@PathVariable Integer id, @RequestParam String description){
        return new ResponseEntity<>(service.updateDescription(id,description),HttpStatus.OK);
    }

    @PutMapping("/add/itemTags/{id}")
    public ResponseEntity<Item> addItemTags(@PathVariable Integer id, @RequestParam String[] tags){
        return new ResponseEntity<>(service.addItemTags(id,tags),HttpStatus.OK);
    }

    @PutMapping("/remove/itemTags/{id}")
    public ResponseEntity<Item> removeItemTags(@PathVariable Integer id, @RequestParam String[] tags){
        return new ResponseEntity<>(service.removeItemTags(id,tags),HttpStatus.OK);
    }

    @PutMapping("/add/rating/{id}")
    public ResponseEntity<Item> addRating(@PathVariable Integer id, @RequestParam Double rating){
        return new ResponseEntity<>(service.addRating(id,rating),HttpStatus.OK);
    }

    @GetMapping("/rating/{id}")
    public ResponseEntity<Double> getRating(@PathVariable Integer id){
        return new ResponseEntity<>(service.getRating(id),HttpStatus.OK);
    }



}
