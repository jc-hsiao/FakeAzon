package com.zipcoder.fakeazon.services;

import com.zipcoder.fakeazon.models.Item;
import com.zipcoder.fakeazon.models.ShoppingCart;
import com.zipcoder.fakeazon.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServices {

    private ShoppingCartRepository cartRepo;

    @Autowired
    public ShoppingCartServices(ShoppingCartRepository cartRepo) {
        this.cartRepo = cartRepo;
    }

    // TODO POST get user for cart then save both
    public ShoppingCart createShoppingCart(ShoppingCart sc, int id){
        return sc;
    }

    // GET
    public ShoppingCart getCart(int id){
        return cartRepo.getOne(id);
    }

    // PUT
    public ShoppingCart addItemToCart(Item item, int id){
        ShoppingCart cart = cartRepo.getOne(id);
        //cart.getItems().add(item);
        return cartRepo.save(cart);
    }

//    public ShoppingCart removeItemFromCart(int itemId, int cartId){
//        ShoppingCart cart = cartRepo.getOne(cartId);
//
//    }


}
