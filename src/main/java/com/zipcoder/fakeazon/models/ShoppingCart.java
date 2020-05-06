package com.zipcoder.fakeazon.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
public class ShoppingCart{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;
    @OneToOne
    private User owner;
    private double total;


    private List<ItemCount> ItemCounts;

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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


    public List<ItemCount> getItemCounts() {
        return ItemCounts;
    }

    public void setItemCounts(List<ItemCount> itemCounts) {
        ItemCounts = itemCounts;
    }



}
