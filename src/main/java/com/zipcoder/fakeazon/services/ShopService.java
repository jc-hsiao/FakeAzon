package com.zipcoder.fakeazon.services;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.zipcoder.fakeazon.exception.NotFoundException;
import com.zipcoder.fakeazon.models.Item;
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

    private final ItemServices itemServices;

    @Autowired
    public ShopService(ShopRepository repo, ItemServices itemServices){
        this.repo = repo;
        this.itemServices = itemServices;
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

    public Shop updateName(Integer shopId, String shopName){
        Shop shop = checkIfShopExists(shopId);
        shop.setName(shopName);
        return saveShop(shop);
    }

    public Shop updateDescription(Integer shopId, String shopDescription){
        Shop shop = checkIfShopExists(shopId);
        shop.setDescription(shopDescription);
        return saveShop(shop);
    }

    public Shop updateLogoUrl(Integer shopId, String shopLogoUrl){
        Shop shop = checkIfShopExists(shopId);
        shop.setLogoUrl(shopLogoUrl);
        return saveShop(shop);
    }

    public Shop addKeywords(Integer shopId, String[] keywords){
        Shop shop = checkIfShopExists(shopId);
        shop.getKeywords().addAll(Arrays.asList(keywords));
        return saveShop(shop);
    }

    public Shop removeKeywords(Integer shopId, String[] keywords){
        Shop shop = checkIfShopExists(shopId);
        List<String> currentKeywords = shop.getKeywords();
        Arrays.stream(keywords).forEach(currentKeywords::remove);
        shop.setKeywords(currentKeywords);
        return saveShop(shop);
    }

    public Shop addItemsToShop(Integer shopId, Item[] items){
        Shop shop = checkIfShopExists(shopId);
        Arrays.stream(items).forEach(item -> {
            item.setShop(shop);
            itemServices.saveItem(item);
        });
        shop.getItems().addAll(Arrays.asList(items));
        return saveShop(shop);
    }

    public Shop removeItemsFromShop(Integer shopId, Item[] items){
        Shop shop = checkIfShopExists(shopId);
        List<Item> shopItems = shop.getItems();
        Arrays.stream(items).forEach(item -> {
            shopItems.remove(item);
            itemServices.deleteItem(item.getId());
        });
        shop.setItems(shopItems);
        return saveShop(shop);
    }



    // Verify Shop Existence

    public Shop checkIfShopExists(Integer shopId){
        Optional<Shop> shop = findOne(shopId);
        if (shop.isPresent())
            return shop.get();
        else throw new NotFoundException("The Shop that you're looking for was not found!");
    }



}
