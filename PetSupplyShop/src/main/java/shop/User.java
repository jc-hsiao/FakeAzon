package shop;

import shop.itemlists.WishList;
import java.util.ArrayList;
import java.util.List;

public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private ShoppingCart shoppingCart;
    private List<Order> orderHistory;
    private List<WishList> wishLists;

    public User(){}

    public User(Integer id, String firstName, String lastName){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        shoppingCart = new ShoppingCart();
        orderHistory = new ArrayList<>();
        wishLists = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //==========================Shopping Cart==========================

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public boolean addItemToCart(Item item, int amount){
        return shoppingCart.add(item,amount);
    }

    public boolean removeItemFromCart(Item item){
        return shoppingCart.remove(item);
    }

    public boolean checkIfCartHas(Item item){return shoppingCart.containsItem(item);}

    public boolean changeQuantityOfItemInCart(Item item, int amount){
        return shoppingCart.changeItemQuantity(item, amount);
    }

    public int getItemQuantity(Item item){
        if(shoppingCart.containsItem(item))
            return shoppingCart.getItemAmount(item);
        else
            return 0;
    }

    public int getNumOfItemInCart(){
        return shoppingCart.getNumOfItems();
    }

    public boolean checkOut(){
        if(shoppingCart.getNumOfItems()==0)
            return false;
        Order newOrder = new Order(this);
        orderHistory.add(newOrder);
        shoppingCart.clearCart();
        return true;
    }

    //==========================shop.Order History==========================

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public int getNumOfOrderHistory() {
        return orderHistory.size();
    }

    //public void setOrderHistory(List<Order> orderHistory) {
    //    this.orderHistory = orderHistory;
    //}

    //==========================Wish Lists==========================

    public List<WishList> getWishLists() {
        return wishLists;
    }

    //public void setWishLists(List<WishList> wishLists) {
        //this.wishLists = wishLists;
    //}

    public void createWishList(int listId, String wishListName){
        WishList newWishList = new WishList(listId, wishListName);
        wishLists.add(newWishList);
    }

    public void renameWishList(int listId, String newName){
        wishLists.forEach( l -> {
        if(l.getId()==listId)
            l.setListName(newName); });
    }

    public WishList findWishList(int listId){
        for (WishList l :wishLists) {
            if(l.getId()==listId)
                return l;
        }
        return null;
    }

    public void removeWishList(int listId){
        wishLists.removeIf(l -> l.getId()==listId);
    }

    public boolean addItemToWishList(Item item, int listId){
        for (WishList l:wishLists) {
            if(l.getId()==listId) {
                l.addItem(item);
                return true;
            }
        }
        return false;
    }

    public boolean removeItemFromWishList(Item item, int listId){
        for (WishList l:wishLists) {
            if(l.getId()==listId && l.containItem(item.getItemID())) {
                l.addItem(item);
                return true;
            }
        }
        return false;
    }

    public void clearItemInList(int listId){
        wishLists.forEach( l -> {
            if(l.getId()==listId)
                l.clearItems();
        });
    }


}
