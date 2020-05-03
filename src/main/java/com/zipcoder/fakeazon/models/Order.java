package com.zipcoder.fakeazon.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;

    private Date date;

    private Double totalPrice;

    @ManyToOne
    private User user;

    @OneToMany
    private List<ItemCount> itemCounts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ItemCount> getItems() {
        return itemCounts;
    }

    public void setItems(List<ItemCount> itemCounts) {
        this.itemCounts = itemCounts;
    }
}
