package com.zipcoder.fakeazon.models;

import javax.persistence.*;
import java.util.Map;

@Entity
public class ShoppingCart{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    @OneToOne
    private User owner;

    @ElementCollection
    @CollectionTable(name = "shopping_cart_items",
            joinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "item_id")
    @Column
    private Map<Item, Integer> items;

    public ShoppingCart(){}

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
