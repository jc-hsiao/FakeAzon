package com.zipcoder.fakeazon.models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ItemCount {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer countId;

    private int itemId;
    private int amount;
    @ManyToOne
    @JsonIgnore
    private ShoppingCart cart;

    public ItemCount() { }

    public Integer getCountId() {
        return countId;
    }

    public void setCountId(Integer countId) {
        this.countId = countId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }
}
