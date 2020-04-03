package com.zipcoder.fakeazon.models;

import javax.persistence.*;
import java.util.Map;

@Entity
public class Order {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    @ManyToOne
    private User user;

    @ElementCollection
    @CollectionTable(name = "item_to_shopping_cart",
            joinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "item_id")
    @Column
    private Map<Item, Integer> items;
}
