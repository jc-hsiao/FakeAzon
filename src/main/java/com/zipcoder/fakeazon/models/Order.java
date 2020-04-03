package com.zipcoder.fakeazon.models;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name="order_table")
public class Order {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    @ManyToOne
    private User user;

    @ElementCollection
    @CollectionTable(name = "order_items",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "item_id")
    @Column
    private Map<Item, Integer> items;
}
