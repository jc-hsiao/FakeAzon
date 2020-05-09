package com.zipcoder.fakeazon.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class WishList{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    private String name;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Item> items;

    public WishList() {
        this.items = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


}
