package com.zipcoder.fakeazon.services;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.zipcoder.fakeazon.models.Item;
import com.zipcoder.fakeazon.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ItemServices {

    private final ItemRepository repo;

    @Autowired
    public ItemServices(ItemRepository repo){
        this.repo = repo;
    }

    public Optional<Item> findOne(Integer itemId){
        return repo.findById(itemId);
    }

    public List<Item> findAll(){
        return repo.findAll();
    }

    public Item saveItem(Item item){
        return repo.save(item);
    }

    public Item updateFullItem(Integer itemId, Item item) throws Exception {
        Item itemToUpdate;
        if(findOne(itemId).isPresent()){
            itemToUpdate = findOne(itemId).get();
            itemToUpdate.setName(item.getName());
            itemToUpdate.setPrice(item.getPrice());
            itemToUpdate.setDescription(item.getDescription());
            itemToUpdate.setImageUrl(item.getImageUrl());
            itemToUpdate.setInventoryCount(item.getInventoryCount());
            itemToUpdate.setItemTags(item.getItemTags());
            itemToUpdate.setShop(item.getShop());
            itemToUpdate.setRating(item.getRating());
            return saveItem(itemToUpdate);
        } else
            throw new Exception("No item with "+ itemId + "exists!");
    }

    public boolean deleteItem(Integer itemId){
        if(findOne(itemId).isPresent()){
            repo.deleteById(itemId);
            return true;
        } else
            return false;
    }

    // INDIVIDUAL UPDATES

    public Item increaseInventoryCount(Integer itemId, Integer amountToIncrease) throws Exception {
        Optional<Item> item = findOne(itemId);
        if(item.isPresent()){
            item.get().setInventoryCount(item.get().getInventoryCount() + amountToIncrease);
          return  repo.save(item.get());
        } else
            throw new Exception("No item with "+ itemId + "exists!");
    }

    public Item decreaseInventoryCount(Integer itemId, Integer amountToDecrease) throws Exception {
        Optional<Item> item = findOne(itemId);
        if(item.isPresent()){
            item.get().setInventoryCount(item.get().getInventoryCount() - amountToDecrease);
            return  repo.save(item.get());
        } else
            throw new Exception("No item with "+ itemId + "exists!");
    }

}
