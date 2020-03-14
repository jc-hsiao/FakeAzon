import java.util.HashMap;

public class ShoppingCart {


    HashMap<Item,Integer> items;
    Double cartValue;


    public ShoppingCart(){
        items = new HashMap<Item, Integer>();
        cartValue = 0.00;
    }

    public boolean add(Item item, int amount){

        if(items.containsKey(item)){
            items.put(item , items.get(item) + amount);
            return true;
        }else if(!items.containsKey(item) && amount > 0){
            items.put(item , amount);
            return true;
        }else{
            return false;
        }
    }
    public boolean remove(Item item){

        if(items.containsKey(item)){
            items.remove(item);
            return true;
        }else {
            return false;
        }
    }

    public boolean replaceItemAmount(Item item , int newAmount){

        if (items.containsKey(item)) {
            items.put(item, newAmount);
            return true;
        } else {
            return false;
        }
    }
    public Integer getItemAmount(Item item){
        return items.get(item);
    }

    public boolean increaseItemAmount(Item item , int amountToIncrease){

        if(items.containsKey(item)){
            items.put(item , items.get(item) + amountToIncrease);
            return true;
        }else{
            return false;
        }
    }

    public Integer getNumOfItems(){
        return this.items.size();
    }

    public Double getCartValue(){
        return this.cartValue;
    }

    public HashMap<Item , Integer> getItems(){
        return this.items;
    }







}
