package com.zipcoder.fakeazon.models;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;
    private String name;
    private double price;
    private int inventoryCount;
    private String imageUrl;
    private String description;

    @ElementCollection
    private List<Double> rating;

    @ElementCollection
    private List<String> itemTags = new ArrayList<>();

    @ManyToOne
    @JoinColumn( name = "shop_id")
    private Shop shop;

    public Item(){}

    public Item(int id){this.id = id;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public List<Double> getRating() {
        return rating;
    }

    public void setRating(List<Double> rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getItemTags() {
        return itemTags;
    }

    public void setItemTags(List<String> itemTags) {
        this.itemTags = itemTags;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
