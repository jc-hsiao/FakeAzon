package com.zipcoder.fakeazon.services;

import com.zipcoder.fakeazon.models.Item;
import com.zipcoder.fakeazon.models.Shop;
import com.zipcoder.fakeazon.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    private final ShopRepository repo;

    @Autowired
    public ShopService(ShopRepository repo){
        this.repo = repo;
    }

    public Optional<Shop> findOne(Integer shopId){
        return repo.findById(shopId);
    }

    public List<Shop> findAll(){
        return repo.findAll();
    }

    public Shop saveShop(Shop shop){
        return repo.save(shop);
    }

    public boolean deleteShop(Integer shopId){
        if(findOne(shopId).isPresent()){
            repo.deleteById(shopId);
            return true;
        } else
            return false;
    }

    // Verify Shop Existence
    public Shop checkIfItemExists(Integer shopId) throws Exception {
        Optional<Shop> shop = findOne(shopId);
        if (shop.isPresent())
            return shop.get();
        else throw new Exception("No shop with "+ shopId + "exists!");
    }

}
