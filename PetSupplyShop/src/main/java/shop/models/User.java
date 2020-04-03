package shop.models;
import shop.itemlists.WishList;
import shop.models.Item;
import shop.models.Order;
import shop.models.ShoppingCart;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    private ShoppingCart shoppingCart;
    private List<Order> orderHistory;
    private List<WishList> wishLists;

    public User(){}

    public User(String username, String firstName, String lastName, String password, String email){
        this.firstName=firstName;
        this.lastName=lastName;
        this.userName = username;
        this.password = password;
        this.email = email;
        shoppingCart = new ShoppingCart();
        orderHistory = new ArrayList<>();
        wishLists = new ArrayList<>();
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

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    //==========================shop.models.Order History==========================

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
