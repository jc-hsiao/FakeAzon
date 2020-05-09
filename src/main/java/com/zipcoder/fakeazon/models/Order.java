package com.zipcoder.fakeazon.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private User user;
    @OneToOne
    private ShoppingCart cart;
    private LocalDate datePlaced;
    private int status;

    public Order() {}

    public Order(Integer id, int status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public LocalDate getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(LocalDate datePlaced) {
        this.datePlaced = datePlaced;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
