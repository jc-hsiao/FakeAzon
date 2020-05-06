package com.zipcoder.fakeazon.services;

import com.zipcoder.fakeazon.models.ItemCount;
import com.zipcoder.fakeazon.models.Shop;
import com.zipcoder.fakeazon.models.ShoppingCart;
import com.zipcoder.fakeazon.models.User;
import com.zipcoder.fakeazon.repositories.ItemCountRepository;
import com.zipcoder.fakeazon.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServices {

    private ShoppingCartRepository cartRepo;

    @Autowired
    private ItemCountRepository itemCountRepo;

    @Autowired
    public ShoppingCartServices(ShoppingCartRepository cartRepo) {
        this.cartRepo = cartRepo;
    }
    // POST
    public ShoppingCart createShoppingCart(ShoppingCart cart){
        return cartRepo.save(cart);
    }
    // GET
    public Optional<ShoppingCart> findOne(int id){
        return cartRepo.findById(id);
    }

    public List<ShoppingCart> findAll(){
        return cartRepo.findAll();
    }

    public ShoppingCart findCartByUser(int id){
        return cartRepo.findShoppingCartByOwner_Id(id);
    }
    // PUT
    public ShoppingCart addItemCountToCart(int itemCountId, int cartId){
        ShoppingCart original = cartRepo.getOne(cartId);
        ItemCount itemCount = itemCountRepo.getOne(itemCountId);
        if (checkIfItemCountIsValid(itemCount.getAmount(), itemCountId)) {
            original.getItemCounts().add(itemCount);
            itemCountRepo.save(itemCount);
        }
        return cartRepo.save(original);
    }

    public ShoppingCart removeItemCountFromCart(int itemCountId, int cartId){
        ShoppingCart cart = cartRepo.getOne(cartId);
        ItemCount itemCount = itemCountRepo.getOne(itemCountId);
        cart.getItemCounts().remove(itemCount);
        itemCountRepo.save(itemCount);
        return cartRepo.save(cart);
    }

//    public ShoppingCart increaseItemCountInCart(int itemCountId, int cartId, int amount){
//        ShoppingCart cart = cartRepo.getOne(cartId);
//
//    }

//    public ShoppingCart decreaseItemCount(int quantity, int cartId, int itemCountId){
//        ShoppingCart cart = cartRepo.getOne(cartId);
//        ItemCount itemCount = itemCountRepo.getOne(itemCountId);
//        if(cart.getItemCounts().contains(itemCount)){
//            int original = itemCount.getAmount();
//            itemCount.setAmount(original-quantity);
//            itemCountRepo.save(itemCount);
//        }
//        return cartRepo.save(cart);
//    }

    public boolean checkIfItemCountIsValid(int amount, int itemCountId){
        ItemCount itemCount = itemCountRepo.getOne(itemCountId);
        return itemCount.getAmount() > amount;
    }

}
