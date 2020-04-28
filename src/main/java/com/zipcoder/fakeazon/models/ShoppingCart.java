package com.zipcoder.fakeazon.models;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
public class ShoppingCart{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    @OneToOne
    private User owner;

    private Double totalPrice;

    @OneToMany
    private List<ItemCount> itemCounts;

    public ShoppingCart(){}

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ItemCount> getItems() {
        return itemCounts;
    }

    public void setItems(List<ItemCount> itemCounts) {
        this.itemCounts = itemCounts;
    }
}
