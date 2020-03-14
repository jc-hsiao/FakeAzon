import java.util.Date;

public class Order {

    User user;
    ShoppingCart shoppingCart;
    Date date;

    public Order(User userIn){
        this.user = userIn;
        shoppingCart = userIn.getShoppingCart();
        this.date = new Date();
    }
    public Date getDate(){
        return this.date;
    }
    public ShoppingCart getShoppingCart(){
        return this.shoppingCart;
    }
    public User getUser(){
        return this.user;
    }

}
