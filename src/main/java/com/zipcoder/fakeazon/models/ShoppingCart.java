package com.zipcoder.fakeazon.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    private User owner;
    private double total;
    @OneToMany
    private List<ItemCount> itemCounts;

    public ShoppingCart(){
        this.total = 0.00;
        this.itemCounts = new ArrayList<>();
    }

    public ShoppingCart(Integer id, User owner, double total) {
        this.id = id;
        this.owner = owner;
        this.total = total;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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
        this.itemCounts = itemCounts;
    }

}
