import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {


    private HashMap<Item,Integer> items;

    public ShoppingCart(){
        items = new HashMap<>();
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

    public boolean changeItemQuantity(Item item , int newAmount){
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

    public Integer getNumOfItems(){
        return this.items.size();
    }

    public Double getItemTotal(Item item){
        return items.get(item)*item.getPrice();
    }

    public Double getGrandTotal(){
        Double sum = 0.0;
        if(items.size()==0)
            return sum;
        for (Map.Entry<Item,Integer> e :items.entrySet()) {
            sum += getItemTotal(e.getKey());
        }
        return sum;
    }

    public HashMap<Item , Integer> getItems(){
        return this.items;
    }


}
