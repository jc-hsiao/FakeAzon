package com.zipcoder.fakeazon.services;

import com.zipcoder.fakeazon.models.Shop;
import com.zipcoder.fakeazon.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    // Updates

    public Shop updateName(Integer shopId, String shopName) throws Exception {
        Shop shop = checkIfShopExists(shopId);
        shop.setName(shopName);
        return saveShop(shop);
    }

    public Shop updateDescription(Integer shopId, String shopDescription) throws Exception {
        Shop shop = checkIfShopExists(shopId);
        shop.setDescription(shopDescription);
        return saveShop(shop);
    }

    public Shop updateLogoUrl(Integer shopId, String shopLogoUrl) throws Exception {
        Shop shop = checkIfShopExists(shopId);
        shop.setLogoUrl(shopLogoUrl);
        return saveShop(shop);
    }

    public Shop addKeywords(Integer shopId, String[] keywords) throws Exception{
        Shop shop = checkIfShopExists(shopId);
        shop.getKeywords().addAll(Arrays.asList(keywords));
        return saveShop(shop);
    }

    public Shop removeKeywords(Integer shopId, String[] keywords) throws Exception{
        Shop shop = checkIfShopExists(shopId);
        List<String> currentKeywords = shop.getKeywords();
        Arrays.stream(keywords).forEach(currentKeywords::remove);
        shop.setKeywords(currentKeywords);
        return saveShop(shop);
    }

    // Verify Shop Existence

    public Shop checkIfShopExists(Integer shopId) throws Exception {
        Optional<Shop> shop = findOne(shopId);
        if (shop.isPresent())
            return shop.get();
        else throw new Exception("No shop with "+ shopId + "exists!");
    }



}
