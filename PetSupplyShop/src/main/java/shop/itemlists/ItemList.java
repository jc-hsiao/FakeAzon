package shop.itemlists;

import shop.Item;

import java.util.HashSet;
import java.util.Set;

public abstract class ItemList {

    private Set<Item> items = new HashSet<>();

    public ItemList(){}

    public Item getItem(String itemId){
        for (Item i:items) {
            if(i.getItemID()==itemId)
                return i;
        }
        return null;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(String itemId){
        Item item = getItem(itemId);
        items.remove(item);
    }

    public boolean containItem(String itemId){
        Item item = getItem(itemId);
        return items.contains(item);
    }

    public void clearItems(){
        items.clear();
    }

    public int getListSize(){
        return items.size();
    }

}
