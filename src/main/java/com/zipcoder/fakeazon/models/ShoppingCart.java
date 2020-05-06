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
    private List<ItemCount> itemCounts;

    public ShoppingCart(){
        this.total = 0.00;
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


    public List<ItemCount> getItemCounts() {
        return itemCounts;
    }

    public void setItemCounts(List<ItemCount> itemCounts) {
        itemCounts = itemCounts;
    }



}
