import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private ShoppingCart shoppingCart;
    private List<Order> orderHistory;
    private List<ItemList> wishLists;

    public User(){}

    public User(int id, String firstName, String lastName){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        shoppingCart = new ShoppingCart();
        orderHistory = new ArrayList<>();
        wishLists = new ArrayList<>();
    }

    public int getId() {
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

    public boolean changeQuantity(Item item, int amount){
        return shoppingCart.changeItemQuantity(item, amount);
    }

    public int getItemQuantity(Item item){
        return shoppingCart.getItemAmount(item);
    }

    public int getNumOfItemInCart(){
        return shoppingCart.getNumOfItems();
    }

    //==========================Order History==========================

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    //==========================Wish Lists==========================

    public List<ItemList> getWishLists() {
        return wishLists;
    }

    public void setWishLists(List<ItemList> wishLists) {
        this.wishLists = wishLists;
    }

    public void renameWishList(int listId, String newName){
        wishLists.forEach( l -> {
        if(l.getId()==listId)
            l.setListName(newName); });
    }

    public void createWishList(int listId, String wishListName){
        ItemList newWishList = new ItemList(listId, wishListName);
        wishLists.add(newWishList);
    }

    public void removeWishList(int listId){
        wishLists.removeIf(l -> l.getId()==listId);
    }

    public boolean addItemToWishList(Item item, int listId){
        for (ItemList l:wishLists) {
            if(l.getId()==listId) {
                l.addItem(item);
                return true;
            }
        }
        return false;
    }

    public boolean removeItemFromWishList(Item item, int listId){
        for (ItemList l:wishLists) {
            if(l.getId()==listId && l.containItem(item.getItemID())) {
                l.addItem(item);
                return true;
            }
        }
        return false;
    }

    public void clearItemFromList(int listId){
        wishLists.forEach( l -> {
            if(l.getId()==listId)
                l.clearItems();
        });
    }


}
