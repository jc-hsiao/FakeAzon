package shop;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {

    User user;
    Map<Item,Integer> shoppingCart;
    Date date;

    public Order(User userIn){
        this.user = userIn;
        shoppingCart = new HashMap<>();
        shoppingCart.putAll(userIn.getShoppingCart().getItems());
        this.date = new Date();
    }
    public Date getDate(){
        return this.date;
    }
    public Map<Item,Integer> getShoppingCart(){
        return this.shoppingCart;
    }
    public User getUser(){
        return this.user;
    }

}
