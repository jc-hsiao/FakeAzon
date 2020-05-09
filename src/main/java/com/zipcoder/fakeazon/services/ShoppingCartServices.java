package com.zipcoder.fakeazon.services;

import com.zipcoder.fakeazon.models.ItemCount;
import com.zipcoder.fakeazon.models.ShoppingCart;
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

//    public ItemCount createItemCount(ItemCount itemCount){
//        return itemCountRepo.save(itemCount);
//    }
    // GET
    public Optional<ShoppingCart> findOne(Integer id){
        return cartRepo.findById(id);
    }

    public List<ShoppingCart> findAll(){
        return cartRepo.findAll();
    }

    public Optional<ShoppingCart> findCartByUser(Integer id){
        return cartRepo.findShoppingCartByOwner_Id(id);
    }
    // PUT
    public ShoppingCart addItemCountToCart(Integer cartId, int countId){
        ShoppingCart original = cartRepo.getOne(cartId);
        ItemCount itemCount = itemCountRepo.getOne(countId);
        itemCountRepo.save(itemCount);
        original.getItemCounts().add(itemCount);
        return cartRepo.save(original);
    }

    public ShoppingCart removeItemCountFromCart(Integer itemCountId, Integer cartId){
        ShoppingCart cart = cartRepo.getOne(cartId);
        ItemCount itemCount = itemCountRepo.getOne(itemCountId);
        cart.getItemCounts().remove(itemCount);
        itemCountRepo.save(itemCount);
        return cartRepo.save(cart);
    }

    public ShoppingCart updateItemCountQuantity(int itemCountId, Integer cartId, int quantity){
        ShoppingCart cart = cartRepo.getOne(cartId);
        ItemCount itemCount = itemCountRepo.getOne(itemCountId);
        List<ItemCount> cartItems = cart.getItemCounts();
        for(ItemCount ic: cartItems){
            if(ic == itemCount && cartItems.contains(ic)){
                itemCount.setAmount(quantity);
                int index = cartItems.indexOf(ic);
                ItemCount updatedItem = cartItems.get(index);
                itemCountRepo.save(updatedItem);
            }
        }
        return cartRepo.save(cart);
    }

    public boolean clearAllItemsFromCart(Integer ownerId){
        Optional<ShoppingCart> cart = cartRepo.findShoppingCartByOwner_Id(ownerId);
        if(!cart.get().getItemCounts().isEmpty()){
            cart.get().getItemCounts().clear();
            cartRepo.save(cart.get());
            return true;
        }
        return false;
    }

}
