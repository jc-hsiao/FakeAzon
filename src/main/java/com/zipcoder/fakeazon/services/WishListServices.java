package com.zipcoder.fakeazon.services;

import com.zipcoder.fakeazon.exception.IllegalArgumentException;
import com.zipcoder.fakeazon.exception.NotFoundException;
import com.zipcoder.fakeazon.models.Item;
import com.zipcoder.fakeazon.models.User;
import com.zipcoder.fakeazon.models.WishList;
import com.zipcoder.fakeazon.repositories.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishListServices {

    final private WishListRepository repo;
    final private UserServices userServices;
    final private ItemServices itemServices;

    @Autowired
    public WishListServices(WishListRepository repo, UserServices userServices, ItemServices itemServices) {
        this.repo = repo;
        this.userServices = userServices;
        this.itemServices = itemServices;
    }

    public Optional<WishList> findById(Integer id){
        return repo.findById(id);
    }

    //create wishlist by userId
    public WishList createWishList(WishList wishList, Integer userId){
        Optional<User> u = userServices.findUserById(userId);
        u.ifPresent(wishList::setUser);
        return repo.save(wishList);
    }

    //change name of the wishlist
    public WishList changeName(Integer id, String name){
        WishList w = checkIfWishListExists(id);
        w.setName(name);
        return repo.save(w);
    }

    //add Item
    public WishList addItem(Integer listId, Integer itemId){
        WishList wList = checkIfWishListExists(listId);
        Item newItem = itemServices.checkIfItemExists(itemId);
        List<Item> itemList = wList.getItems();
        //check if the item is already in the list
        for(Item it:itemList){
            if(it.getId().equals(newItem.getId())){
                // item duplicate, terminate
                throw new IllegalArgumentException();
            }
        }
        itemList.add(newItem);
        return repo.save(wList);
    }


    //remove Item
    public WishList removeItem(Integer listId, Integer itemId){
        WishList wList = checkIfWishListExists(listId);
        Item newItem = itemServices.checkIfItemExists(itemId);
        List<Item> itemList = wList.getItems();
        //check if the item is in the list
        Item toBeRemoved = null;
        for(Item it:itemList){
            if(it.getId().equals(newItem.getId())) {
                toBeRemoved = it;
                break;
            }
        }
        if(toBeRemoved == null)
            throw new NotFoundException("The item is not even in the list!");
        else
           itemList.remove(toBeRemoved);

        return repo.save(wList);
    }

    public WishList deleteWishList(Integer id){
        WishList wList = checkIfWishListExists(id);
        repo.delete(wList);
        return wList;
    }

    public WishList checkIfWishListExists(Integer id){
        Optional<WishList> wl = findById(id);
        if (wl.isPresent())
            return wl.get();
        else throw new NotFoundException("No data found for this wishlist.");
    }


}
