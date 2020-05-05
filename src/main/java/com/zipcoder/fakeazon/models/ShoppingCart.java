package com.zipcoder.fakeazon.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;
    @OneToOne
    private User owner;
    private double total;
    @OneToMany
    private List<Item> items;
    @ElementCollection
    private List<Integer> itemCounts;

    public ShoppingCart(){
        this.total = 0.00;
        this.items = new ArrayList<>();
        this.itemCounts = new ArrayList<>();
    }

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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Integer> getItemCounts() {
        return itemCounts;
    }

    public void setItemCounts(List<Integer> itemCounts) {
        this.itemCounts = itemCounts;
    }
}
