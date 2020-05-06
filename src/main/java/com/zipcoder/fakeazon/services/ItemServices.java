package com.zipcoder.fakeazon.services;

import com.zipcoder.fakeazon.models.Item;
import com.zipcoder.fakeazon.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public boolean deleteItem(Integer itemId){
        if(findOne(itemId).isPresent()){
            repo.deleteById(itemId);
            return true;
        } else
            return false;
    }

    // INDIVIDUAL UPDATES

    public Item increaseInventoryCount(Integer itemId, Integer amountToIncrease) throws Exception {
        Item item = checkIfItemExists(itemId);
        item.setInventoryCount(item.getInventoryCount() + amountToIncrease);
        return repo.save(item);
    }

    public Item decreaseInventoryCount(Integer itemId, Integer amountToDecrease) throws Exception {
        Item item = checkIfItemExists(itemId);
        item.setInventoryCount(item.getInventoryCount() - amountToDecrease);
        return repo.save(item);
    }

    public Item updateName(Integer itemId, String name) throws Exception {
        Item item = checkIfItemExists(itemId);
        item.setName(name);
        return repo.save(item);
    }

    public Item updatePrice(Integer itemId, Double newPrice) throws Exception {
        Item item = checkIfItemExists(itemId);
        item.setPrice(newPrice);
        return repo.save(item);
    }

    public Item updateImageUrl(Integer itemId, String imageUrl) throws Exception {
        Item item = checkIfItemExists(itemId);
        item.setImageUrl(imageUrl);
        return repo.save(item);
    }

    public Item updateDescription(Integer itemId, String description) throws Exception {
        Item item = checkIfItemExists(itemId);
        item.setDescription(description);
        return repo.save(item);
    }

    public Item addItemTags(Integer itemId, String[] itemTags) throws Exception {
        Item item = checkIfItemExists(itemId);
        List<String> tags = item.getItemTags();
        tags.addAll(Arrays.asList(itemTags));
        item.setItemTags(tags);
        return repo.save(item);
    }

    public Item removeItemTags(Integer itemId, String[] itemTags) throws Exception {
        Item item = checkIfItemExists(itemId);
        List<String> tags = item.getItemTags();
        tags.addAll(Arrays.asList(itemTags));
        Arrays.stream(itemTags).forEach(tags::remove);
        item.setItemTags(tags);
        return repo.save(item);
    }

    public Double getRating(Integer itemId) throws Exception {
        Item item = checkIfItemExists(itemId);
        List<Double> ratings = item.getRating();
        return ratings.stream().collect(Collectors.averagingDouble(Double::valueOf));
    }


    // Verify Item Existence
    public Item checkIfItemExists(Integer itemId) throws Exception {
        Optional<Item> item = findOne(itemId);
        if (item.isPresent())
            return item.get();
        else throw new Exception("No item with "+ itemId + "exists!");
    }

}
